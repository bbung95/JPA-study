import jpql.domain.Member;
import jpql.domain.MemberDto;
import jpql.domain.Team;

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

			/////////////////////
			Team teamA = new Team();
			teamA.setName("팀A");
			em.persist(teamA);

			Team teamB = new Team();
			teamB.setName("팀B");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("회원1");
			member1.setTeam(teamA);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("회원2");
			member2.setTeam(teamA);
			em.persist(member2);

			Member member3 = new Member();
			member3.setUsername("회원3");
			member3.setTeam(teamB);
			em.persist(member3);

			em.flush();
			em.clear();

			// 기본 조인
			// String query = "select m from Member m";
			// entity fetch join
			// String query = "select m from Member m join fetch m.team";
			// collection fetch join
			String query = "select distinct t from Team t join fetch t.members";

			 List<Team> result = em.createQuery(query , Team.class).getResultList();

			for (Team t: result
				 ) {
				// System.out.println("member = " + m.getUsername()+","+m.getTeam().getName());
				// 기본 조회
				// 회원 1 , 팀A(SQL)
				// 회원 2 , 팀A(1차캐시)
				// 회원 3 , 팀B(SQL)

				// Entity fetch 조인
				// 지연로딩없이 즉시로딩과 같이 쿼리 한번에 값을 셋팅

				System.out.println("Team = " + t.getName()+" | Members = "+t.getMembers().size());
				// collection fetch 조인
				// Team = 팀A | Members = 2
				// Team = 팀A | Members = 2
				// Team = 팀B | Members = 1

				// collection fetch  Distinct조인
				// Team = 팀A | Members = 2
				// Team = 팀B | Members = 1
			}
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
