package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.dto.OrderSearch;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  xToOne(ManyToOne, OneToOne)
 *  Order
 *  Order -> Member
 *  Order -> Delivery
 */

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){

        List<Order> orders = orderService.findOrders(new OrderSearch());

        for(Order order : orders ){
            {
                order.getMember();
            }
        }

        return orders;
    }
}
