package com.devbu03.base.service.productTemp;

import com.devbu03.base.dto.ProductTempDTO;
import com.devbu03.base.entity.ProductTempEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public interface ProductTempService {

  BigDecimal updateQuantity(Long id, int quantity);

  ProductTempDTO findById(Long id);

  ProductTempEntity findByIdTemp(Long id, LocalDateTime createAt) ;

  /**
   * thêm một món ăn mới vào menu
   *
   * @param productTempDTO
   * @return
   */
  ProductTempEntity saveNewProduct(ProductTempDTO productTempDTO);

  /**
   * Lấy product theo id
   *
   * @param id
   * @return
   */
  ProductTempDTO getProductById(long id);
}

