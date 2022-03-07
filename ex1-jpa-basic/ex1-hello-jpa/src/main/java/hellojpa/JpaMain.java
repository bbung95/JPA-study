package hellojpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // Persistence를 이용하여 JPA 와 데이터베이스 연결
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();

		try {
			
//			등록
//			Member member = new Member();
//			
//			member.setId(2L);
//			member.setName("HelloB");
//			
//			em.persist(member);
			
//			조회
//			Member findMember = em.find(Member.class, 1L);
			
			List<Member> memberList = em.createQuery("select m from Member as m" , Member.class)
		            		.setFirstResult(0)  // MySQL : LIMIT   , ORACLE : ROWNUM (시작 번호)
				            .setMaxResults(5) // (가져오는 갯수)
				            .getResultList();
			
			for (Member member : memberList) {
				System.out.println("memeber-name : "+member.getName());
			}

// 			업데이트
//			findMember.setName("HelloJPA");
			
//			삭제
//			em.remove(findMember);
			
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
