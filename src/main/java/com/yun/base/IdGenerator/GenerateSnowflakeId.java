package com.yun.base.IdGenerator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Properties;

// import org.hibernate.engine.spi.SharedSessionContractImplementor;

/**
 * @author: yun
 * @createdOn: 2018/7/25 11:19.
 */

// TODO 不能@Autowired,此对象由hibernate管理
// @service
@Component
@Transactional
public class GenerateSnowflakeId implements IdentifierGenerator, Configurable {

    // @Autowired // TODO 不能@Autowired,此对象由hibernate管理
    // private SnowflakeIdWorker snowFlakeIdWorker;

    /**
     * hibernate自定义主键生成规则必须实现 IdentifierGenerator  generate 为默认方法
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {

        Long id = getSlIdWorker().nextId();
        if (id != null) {
            return id;
        } else {
            return null;
        }
    }

    private SnowflakeIdWorker getSlIdWorker() {
        if (SnowflakeIdUtil.snowflakeIdUtil.getSnowflakeIdWorker() == null) {
            throw new RuntimeException("请配置snowflakeIdWorker参数");
        } else {
            return SnowflakeIdUtil.snowflakeIdUtil.getSnowflakeIdWorker();
        }
    }

    /**
     * 加载配置文件
     */
    @Override
    public void configure(Type type, Properties properties, ServiceRegistry d)
            throws MappingException {
    }
}