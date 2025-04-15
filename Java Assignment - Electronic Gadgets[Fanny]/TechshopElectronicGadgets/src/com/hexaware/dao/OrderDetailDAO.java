package com.hexaware.dao;

import com.hexaware.entity.OrderDetail;
import java.util.List;

public interface OrderDetailDAO {
	void insertOrderDetail(OrderDetail detail);
    void updateOrderDetail(OrderDetail detail);
    void deleteOrderDetail(int orderDetailID);
    List<OrderDetail> getOrderDetailsByOrderId(int orderID);

}
