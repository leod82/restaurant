package com.devbu03.base.repository;

import com.devbu03.base.entity.ProductTempEntity;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTempRepository extends JpaRepository<ProductTempEntity, Long> {

  @Query(value = "SELECT * "
      + " FROM PRODUCT_TEMP "
      + " WHERE PRODUCT_ID = 88 AND CREATED_AT <= TO_DATE('2023-08-03', 'YYYY-MM-DD') "
      + " ORDER BY CREATED_AT DESC "
      + "    FETCH FIRST 1 ROW ONLY ",nativeQuery = true)
  ProductTempEntity findByProductIdAndCreatedAt(@Param("productId") Long productId,@Param("createdAt") String createdAt);
}

