package com.yun.base.jpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface Base jpa repository by auto pkId.
 * @param <T> the itemType parameter
 * @author: yun
 * @createdOn: 2018 /7/24 下午9:20.
 */
@NoRepositoryBean
public interface BaseJpaRepositoryByAutoId<T> extends JpaRepository<T, Long> {

    // region --Field

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    // endregion

    // region --Public method

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
