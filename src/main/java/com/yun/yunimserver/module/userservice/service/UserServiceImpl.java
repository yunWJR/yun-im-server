package com.yun.yunimserver.module.userservice.service;

import com.yun.base.Util.ObjectUtil;
import com.yun.base.jpa.Repository.RepositoryHelper;
import com.yun.base.module.Bean.BaseRstBeanHelper;
import com.yun.base.module.Bean.BaseRstModuleType;
import com.yun.base.module.Bean.RstBeanException;
import com.yun.base.token.AuthTokenUtil;
import com.yun.yunimserver.module.BaseServiceImpl;
import com.yun.yunimserver.module.userservice.dtovo.UserVo;
import com.yun.yunimserver.module.userservice.entity.*;
import com.yun.yunimserver.util.RequestUtil;
import com.yun.yunimserver.wsapi.DtoVo.ClientUserDto;
import com.yun.yunimserver.wsapi.DtoVo.ClientUserLoginVo;
import com.yun.yunimserver.wsapi.DtoVo.ClientUserVo;
import com.yun.yunimserver.wsapi.WsApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The itemType User service imp i.
 * @Description:
 * @Author: yun
 * @CreatedOn: 2018 /5/28 14:46.
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    WsApiServiceImpl wsApiService;

    @Autowired
    private UserJpa userJpa;

    @Autowired
    private AuthTokenUtil tokenUtil;

    private RepositoryHelper<User> svRpHlp = new RepositoryHelper<User>("用户");

    private BaseRstBeanHelper rstBeanHelper = new BaseRstBeanHelper(BaseRstModuleType.Service, "UserServiceImpI");

    // @PostConstruct
    // public void initFactory() {
    //     queryFactory = new JPAQueryFactory(entityManager);
    // }

    @Override
    public List<User> getAllUser() {
        // AuthTokenPayload token = AuthTokenUtil.getThreadLocalToken();
        // userUtil.checkAccess(token, UserType.Admin);

        List<User> userList = userJpa.findAll();

        return userList;
    }

    @Override
    public User updateUserInfo(UserInfo info) {
        User lgUser = RequestUtil.getLoginUser();

        if (lgUser.getUserInfo() == null) {
            lgUser.setUserInfo(new UserInfo());
        }

        // 将 null 值取代
        ObjectUtil.copyNonNullProperties(info, lgUser.getUserInfo());

        return lgUser;
    }

    @Override
    public User register(UserAcct acct) {
        User newUs = new User(acct);

        // userservice name 不重复
        if (userJpa.existsByUserAcctName(newUs.getUserAcct().getName())) {
            throwCommonError("用户名已存在");
        }

        // 获取 userservice pkId
        newUs = userJpa.save(newUs);

        //  根据user id更新 token
        newUs = this.updateToken(newUs);

        newUs = userJpa.save(newUs);

        ClientUserDto dto = new ClientUserDto();
        dto.setExtraUserId(newUs.getId().toString());
        ClientUserVo vo = wsApiService.addClientUser(dto);

        return newUs;
    }

    @Override
    public User getValidUser(Long userId) {
        User rst = svRpHlp.findByGlId(userJpa, userId);

        return rst;
    }

    @Override
    public User getUserByName(String userName) {
        User us = userJpa.findUserByUserAcctName(userName);

        if (us != null) {
            return us;
        } else {
            throw RstBeanException.RstComErrBeanWithStr(String.format("未找到%s", userName));
        }
    }

    @Override
    public UserVo login(String name, String pws) {
        User us = userJpa.findUserByUserAcctName(name);

        if (us == null || !us.getUserAcct().getPws().equals(pws)) {
            throw RstBeanException.RstComErrBeanWithStr("用户名或密码错误");
        }

        if (us.getToken() != null && us.getToken().length() > 0) {
            // 加入失效列表 // todo
        }

        us = this.updateToken(us);

        us = userJpa.save(us);

        ClientUserLoginVo vo = wsApiService.clientUserLogin(us.getId().toString(),
                RequestUtil.getDeviceType().toString());

        return new UserVo(us, vo);
    }

    @Override
    public User getUserInfo() {
        User lgUser = RequestUtil.getLoginUser();

        return lgUser;
    }

    private User updateToken(User us) {
        String token = tokenUtil.createToken(us.getId().toString());
        us.setToken(token);

        return us;
    }

    @Override
    public User checkTokenUser(String tokenStr, HttpServletRequest request) {
        QUser qUser = QUser.user;

        User appUser = queryFactory.selectFrom(qUser)
                .where(qUser.token.eq(tokenStr))
                .fetchFirst();

        // token 无效
        if (appUser == null) {
            return null;
        }

        // 用户禁用了
        if (appUser.isInValidUser()) {
            throwCommonError("用户已禁用");
        }

        return appUser;
    }
}
