package com.artigo.dota.service;

import com.artigo.dota.dto.ProductDetailQuantityDTO;
import com.artigo.dota.dto.ProductDetailsDTO;
import com.artigo.dota.dto.ProductImageUrlDTO;
import com.artigo.dota.entity.ProductDO;
import com.artigo.dota.entity.ProductDetailsDO;

import java.util.List;

public interface ProductDetailsService {

    boolean reduceProductQuantity(long productDetailsId, int orderedQuantity);

    boolean checkProductAvailability(long productDetailsId, int orderedQuantity);

    ProductDO getRelatedProduct(ProductDetailsDO productDetailsDO);

    List<ProductDetailsDO> saveAll(List<ProductDetailsDO> productDetailsDOList);

    ProductDetailsDO saveProductDetails(ProductDetailsDTO productDetailsDTO, List<ProductImageUrlDTO> uploadedImagesDTO);

    ProductDetailsDTO getProductById(Long id);

    List<ProductDetailQuantityDTO> getProductDetailQuantities(List<Long> idList);

    List<ProductDetailsDO> deleteProductDetailsList(List<ProductDetailsDO> productDetails);

    ProductDetailsDTO deleteProductDetail(Long id);
}
