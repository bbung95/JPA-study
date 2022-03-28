package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.enums.OrderStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void createOrderTest() throws Exception {
        //given
        Member member = createMember();

        Book book = createBook("JPA TEST BOOk", 10000, 100);

        int orderCount = 20;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findById(orderId);

        Assertions.assertEquals(OrderStatus.ORDER.toString() , String.valueOf(getOrder.getOrderStatus()), "상품 주문시 상태는 ORDER" );
        Assertions.assertEquals(1 , getOrder.getOrderItems().size() , "주문한 상품 종류 수가 정확해야 한다." );
        Assertions.assertEquals(10000 * orderCount , getOrder.getTotalPrice() , "주문 가격은 가격 * 수량이다." );
        Assertions.assertEquals(80 , book.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다." );
    }


    @Test
    public void orderCountOverTest() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook("JPA TEST BOOk", 10000, 100);

        int orderCount = 110;
        //when

        Assertions.assertThrows(NotEnoughStockException.class , () ->{
            orderService.order(member.getId() , book.getId() , orderCount);
        }, "재고 수량 부족 예외가 발생해야 한다.");

        //then
    }
    
    @Test
    public void orderCancelTest() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook("JPA TEST BOOK", 10000, 100);

        int orderCount = 10;

        Long orderId = orderService.order(member.getId(), book.getId() , orderCount);

        //when
        orderService.cencelOrder(orderId);

        //then
        Order getOrder = orderRepository.findById(orderId);

        Assertions.assertEquals(OrderStatus.CANCEL.toString() , String.valueOf(getOrder.getOrderStatus()) ,"주문 취소시 상태는 CANCEL 이다.");
        Assertions.assertEquals(100 , book.getStockQuantity() , "주문이 최소된 상품은 그만큼 재고가 증가해야 한다.");

    }

    private Book createBook(String name, int price, int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("Member1");
        member.setAddress(new Address("서울" , "수유" , "123-123"));
        em.persist(member);
        return member;
    }

}