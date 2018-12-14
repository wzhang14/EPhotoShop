package com.wei.EPhotoShop.dao;

import java.util.List;

import com.wei.EPhotoShop.model.CartInfo;
import com.wei.EPhotoShop.model.OrderDetailInfo;
import com.wei.EPhotoShop.model.OrderInfo;
import com.wei.EPhotoShop.model.PaginationResult;

public interface OrderDAO {
	
	public void saveOrder(CartInfo cartInfo);
	
	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage);
	
	public OrderInfo getOrderInfo(String orderId);
	
	public List<OrderDetailInfo> listOrderDetailInfos(String orderId);

}
