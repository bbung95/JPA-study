package hellojpa;

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

			Team team = new Team();
			team.setName("TEAM");
			em.persist(team);

			Member member = new Member();
			member.setUsername("JPA");
			member.setTeam(team);

			em.persist(member);

			em.flush();
			em.clear();

			Member findMember = em.find(Member.class , member.getId()); // Entity 조회
	//		Member findMember = em.getReference(Member.class , member.getId()); // 하이버네이트 프록시 클래스

			System.out.println("//////////////////");
			System.out.println("findMember.getTeam() = " + findMember.getTeam().getClass()); // 프록시 객체 반환
			System.out.println("//////////////////");

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
