package com.yun.base.jpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface Base jpa repository by uuid.
 * @param <T> the itemType parameter
 * @author: yun
 * @createdOn: 2018 /5/30 10:38.
 */
@NoRepositoryBean
public interface BaseJpaRepositoryByUuid<T> extends JpaRepository<T, String> {

    // region --Field

    // endregion

    // region --Constructor

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
