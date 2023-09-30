package com.devbu03.base.service.productTemp;

import com.devbu03.base.common.Helper;
import com.devbu03.base.dto.ProductDTO;
import com.devbu03.base.dto.ProductTempDTO;
import com.devbu03.base.entity.ProductEntity;
import com.devbu03.base.entity.ProductTempEntity;
import com.devbu03.base.exception.CommandExceptionBuilder;
import com.devbu03.base.repository.ProductTempRepository;
import com.devbu03.base.response.ErrorCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductTempServiceImpl implements ProductTempService {

  private final ProductTempMapper mapper;
  private final ProductTempRepository productTempRepository;
  private final MessageSource messageSource;

  @Override
  public ProductTempEntity findByIdTemp(Long id, LocalDateTime createAt) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String date=createAt.format(formatter);
    return productTempRepository.findByProductIdAndCreatedAt(id,date);
  }

  @Override
  public ProductTempDTO findById(Long id) {
    ProductTempEntity resultQuery = productTempRepository.findById(id).get();
    return mapper.getProductTempDTOFromEntity(resultQuery);
  }
  @Override
  @Transactional
  public BigDecimal updateQuantity(Long id, int quantity) {
    if (quantity <= 0) {
      throw CommandExceptionBuilder.exception(
          new ErrorCode(102, messageSource.getMessage("error.product.quantity",
              new Object[]{id}, LocaleContextHolder.getLocale())));
    }
    ProductTempEntity productTempEntity = productTempRepository.findById(id)
        .orElseThrow(() -> CommandExceptionBuilder.exception(
            new ErrorCode(100, messageSource.getMessage("error.product.notFoundId",
                new Object[]{id}, LocaleContextHolder.getLocale()))));
    Integer quantityActual = productTempEntity.getQuantity();
    if (quantityActual < quantity) {
      throw CommandExceptionBuilder.exception(new ErrorCode(101,
          messageSource.getMessage("error.product.quantityNotEnough",
              new Object[]{productTempEntity.getName()}, LocaleContextHolder.getLocale())));
    } else {
      productTempEntity.setQuantity(quantityActual - quantity);
      productTempRepository.save(productTempEntity);
    }
    return productTempEntity.getPrice();
  }
  /**
   * Thêm mới một món ăn
   *
   * @param productTempDTO
   * @return
   */
  @Override
  public ProductTempEntity saveNewProduct(ProductTempDTO productTempDTO) {
    ProductTempEntity productTempEntity = mapper.getProductTempEntityFromDTO(productTempDTO);
    productTempEntity.setCreatedAt(Helper.getLocalDateTimeNow());
    return productTempRepository.save(productTempEntity);
  }

  /**
   * lấy thông tin product
   *
   * @param id
   * @return
   */
  @Override
  public ProductTempDTO getProductById(long id) {
    ProductTempEntity productTempEntity = productTempRepository.findById(id).get();
    return mapper.getProductTempDTOFromEntity(productTempEntity);
  }

}

