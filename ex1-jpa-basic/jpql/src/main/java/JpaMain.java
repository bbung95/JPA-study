import jpql.domain.Member;
import jpql.domain.MemberDto;

import javax.persistence.*;
import java.util.List;
import java.util.Queue;

public class JpaMain {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // Persistence를 이용하여 JPA 와 데이터베이스 연결
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();

		try {

			//////////////////////
			// TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
			// 타입이 명확할때
			// Query query2 = em.createQuery("select m.username , m.age from Member m");
			// 타입이 명확하지 않을때 username(String) , age(int)
			//////////////////////


			//////////////////////
			// List<MemberDto> resultList = em.createQuery("select new jpql.domain.MemberDto(m.username , m.age) from Member m").getResultList();
			// new를 통한 DTO 조회 , 생성자 생성 필요
			//////////////////////

			/////////////////////
			// List<MemberDto> resultList = em.createQuery("select new jpql.domain.MemberDto(m.username , m.age) from Member m")
			//		.setFirstResult(1) // 시작 번호
			//		.setMaxResults(10) // 가져오는 ROW 수
			//		.getResultList();
			// JPA 방언을 통한 페이징 처리 Oracle : rownum , MySQL : LIMIT
			/////////////////////


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
