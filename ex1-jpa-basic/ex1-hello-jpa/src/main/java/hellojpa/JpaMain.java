package hellojpa;

import hellojpa.extend.Child;
import hellojpa.extend.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // Persistence를 이용하여 JPA 와 데이터베이스 연결
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();

		try {

			Child child1 = new Child();
			Child child2 = new Child();

			Parent parent = new Parent();
			parent.addChild(child1);
			parent.addChild(child2);

			em.persist(parent); // cascade를 통한 영속성 관리

			em.flush();
			em.clear();

			Parent parent1 = em.find(Parent.class , parent.getId());
			em.remove(parent1); // child 또한 삭제 / 고아객체를 통한 자식 관리

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
