package study.datajpa.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByIdAndAndTeamId(Long memberId, Long teamId);

    @Modifying(clearAutomatically = true) // 벌크성 수정쿼리는 필요 => excuteUpdate 실행
    @Query("update Member m set m.age = m.age +1 where m.age >= :age")
    public int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    public List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
//    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);
    // 복잡한 쿼리는 JPQL을 사용해야하지만 간단한건 @EntityGraph 사용
}
