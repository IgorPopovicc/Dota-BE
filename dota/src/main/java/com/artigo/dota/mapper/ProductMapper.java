package com.artigo.dota.mapper;
import com.artigo.dota.dto.ProductDTO;
import com.artigo.dota.entity.ProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {
    @Mapping(target = "isDeleted", expression = "java(mapIsDeleted())")
    ProductDO dtoToEntity(ProductDTO productDTO);
    ProductDTO entityToDto(ProductDO product);
    default Boolean mapIsDeleted() {
        return false;
    }

}
