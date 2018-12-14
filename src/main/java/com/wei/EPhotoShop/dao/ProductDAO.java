package com.wei.EPhotoShop.dao;

import com.wei.EPhotoShop.entity.Product;
import com.wei.EPhotoShop.model.PaginationResult;
import com.wei.EPhotoShop.model.ProductInfo;


public interface ProductDAO {
    
	public Product findProduct(String code);
	public ProductInfo findProductInfo(String code);
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNaviagtionPage);
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNaviagtionPage, String likeName);
	public void save(ProductInfo productInfo);
}
