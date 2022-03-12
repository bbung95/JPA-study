package jpabook.jpashop.domain;

import org.hibernate.type.LocalDateTimeType;
import org.hibernate.type.LocalDateType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Date;

public class JpaMain {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // Persistence를 이용하여 JPA 와 데이터베이스 연결
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();

		try {

			Member member = new Member();
			member.setCity("서울");
			member.setName("뻥뻥이");
			member.setStreet("상계로");
			member.setZipcode("1234");

			em.persist(member);

			Item item = new Item();
			item.setName("맥북");
			item.setPrice(3333333);
			item.setStockQuantity(1);

			em.persist(item);

			Order order = new Order();
			order.setMember(member);
			order.setStatus(OrderStatus.ORDER);

			em.persist(order);

			OrderItem orderItem = new OrderItem();
			orderItem.setItem(item);
			orderItem.setOrder(order);
			orderItem.setCount(2);
			orderItem.setOrderPrice(item.getPrice() * orderItem.getCount());

			em.persist(orderItem);

			tx.commit(); // 로직 정상 수행
		} catch(Exception e) {
			tx.rollback(); // 로직 오류 발생시 롤백
		} finally {
			// close
 			em.close(); // EntityManager는 데이터커넥션을 물고 있기 때문에 꼭 닫아줘야한다.
		}

		emf.close();
	}
}
