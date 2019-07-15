package com.yun.base.jpa.Repository;

import com.yun.base.Util.JrpUtil;
import com.yun.base.module.Bean.BaseRstBean;
import com.yun.base.module.Bean.BaseRstBeanHelper;
import com.yun.base.module.Bean.BaseRstModuleType;
import com.yun.base.module.Bean.RstBeanException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * The itemType Repository helper.
 * @param <T> the itemType parameter
 * @author: yun
 * @createdOn: 2018 /5/30 09:44.
 */
@Component
public class RepositoryHelper<T> {

    // region --Field

    private String entityName = "对象";

    private BaseRstBeanHelper rstBeanHelper = new BaseRstBeanHelper(BaseRstModuleType.Dao, "RepositoryHelper");

    // endregion

    // region --Constructor

    public RepositoryHelper() {
    }

    public RepositoryHelper(String entityName) {
        this.entityName = entityName;

        rstBeanHelper.setModuleData(this.entityName);
    }

    // endregion

    // region --static method

    public String checkValidUuid(String id) {
        if (StringUtils.isBlank(id)) {
            return "id无效(1)";
        }

        if (id.length() != 32 && id.length() != 36) {
            // 长度不对
            return "id无效(2)";
        }

        return null;
    }

    public boolean isValidUuid(String id) {
        String err = checkValidUuid(id);

        return err == null;
    }

    // endregion

    // region --Getter and Setter

    /**
     * Gets entity name.
     * @return the entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Sets entity name.
     * @param entityName the entity name
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    // endregion

    // region --Public method

    /**
     * Find by pkId base rst bean.
     * @param jpa the jpa
     * @param id  the pkId
     * @return the base rst bean
     */
    public BaseRstBean findById(BaseJpaRepositoryByUuid<T> jpa, String id) {
        if (jpa == null) {
            return rstBeanHelper.ComErrBean("无法获取Rp对象");
        }

        String err = checkValidUuid(id);
        if (err != null) {
            return rstBeanHelper.ComErrBean(err);
        }

        T item = JrpUtil.findById(jpa, id);
        if (item != null) {
            return rstBeanHelper.SurBean(item);
        } else {
            return rstBeanHelper.ComErrBean(String.format("未找到%s", entityName));
        }
    }

    public T findByGlId(BaseJpaRepositoryByAutoId<T> jpa, Long id) {
        if (jpa == null) {
            throw RstBeanException.RstComErrBeanWithStr("无法获取Rp对象");
        }

        if (id == null) {
            throw RstBeanException.RstComErrBeanWithStr("id无效");
        }

        T item = JrpUtil.findById(jpa, id);
        if (item != null) {
            return item;
        } else {
            throw RstBeanException.RstComErrBeanWithStr(String.format("未找到%s", entityName));
        }
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
