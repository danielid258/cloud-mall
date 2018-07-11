package com.daniel.order.service.impl;

import com.daniel.order.dto.OrderDTO;
import com.daniel.order.enums.OrderStatusEnum;
import com.daniel.order.enums.PayStatusEnum;
import com.daniel.order.model.OrderDetail;
import com.daniel.order.model.OrderMaster;
import com.daniel.order.remote.ProductApi;
import com.daniel.order.repository.OrderDetailRepository;
import com.daniel.order.repository.OrderMasterRepository;
import com.daniel.order.service.OrderService;
import com.daniel.order.util.KeyUtil;
import com.daniel.product.common.model.CommonProduct;
import com.daniel.product.common.model.CommonProductStock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Daniel on 2018/7/10.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductApi productApi;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        //查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<CommonProduct> productInfoList = productApi.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (CommonProduct productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //扣库存(调用商品服务)
        List<CommonProductStock> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CommonProductStock(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productApi.decreaseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
