package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.OrderForm;
import jpabook.jpashop.dto.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order/")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/new")
    public String orderForm(Model model){

        List<Member> members = memberService.findMember();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/new")
    public String order(OrderForm form){

        orderService.order(form.getMemberId(), form.getItemId(), form.getCount());

        return "redirect:/order/";
    }

    @GetMapping("/")
    public String orderList(Model model , OrderSearch param){

        List<Order> orders = orderService.findOrders(param);

        model.addAttribute("orderSearch", param);
        model.addAttribute("orders" , orders);

        return "order/orderList";
    }

    @PostMapping("/{orderId}/cancel")
    public String orderCancel(@PathVariable Long orderId){
        orderService.cencelOrder(orderId);

        return "redirect:/order/";
    }
}
