package com.artigo.dota.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailQuantitiesRequestDTO {

    private List<Long> productDetailIds;
}
