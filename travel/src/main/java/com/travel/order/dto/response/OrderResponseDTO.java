package com.travel.order.dto.response;

import com.travel.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class OrderResponseDTO {

    private Long orderId;

    private Long productId;

    private String productName;

    private String productThumbnail;

    private Integer productPrice;

    private LocalDateTime orderDate;

    private String optionName;

    private Integer productProductQuantity;

    private OrderStatus orderStatus;

    @Builder
    public OrderResponseDTO(Long orderId, Long productId, String productName, String productThumbnail, Integer productPrice, LocalDateTime orderDate, String optionName, Integer productProductQuantity, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productThumbnail = productThumbnail;
        this.productPrice = productPrice;
        this.orderDate = orderDate;
        this.optionName = optionName;
        this.productProductQuantity = productProductQuantity;
        this.orderStatus = orderStatus;
    }
}
