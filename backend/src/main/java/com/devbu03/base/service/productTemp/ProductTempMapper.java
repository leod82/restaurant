package com.devbu03.base.service.productTemp;

import com.devbu03.base.dto.ProductTempDTO;
import com.devbu03.base.entity.ProductTempEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductTempMapper {
  
  ProductTempEntity
  getProductTempEntityFromDTO(ProductTempDTO dto);

  ProductTempDTO getProductTempDTOFromEntity(ProductTempEntity entity);

}
