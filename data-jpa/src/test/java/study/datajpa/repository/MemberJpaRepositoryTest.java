package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamJpaRepository teamJpaRepository;
    
    @Test
    public void testMember() throws Exception {
        //given
        Member member = new Member("맴버1");

        //when
        Member saveMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(saveMember.getId());

        //then
        assertThat(saveMember.getId()).isEqualTo(findMember.getId());
        assertThat(saveMember.getUsername()).isEqualTo(findMember.getUsername());
        assertThat(saveMember).isEqualTo(findMember);
    }
    
    @Test
    public void basicCRUD() throws Exception {
        //given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        // 단건 조회
        Member findMember1 = memberJpaRepository.find(member1.getId());
        Member findMember2 = memberJpaRepository.find(member2.getId());
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // 리스트 조회
        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트
        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제
        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);

        long deleteCount = memberJpaRepository.count();
        assertThat(deleteCount).isEqualTo(0);

    }
    
    @Test
    public void namingQueryTest() throws Exception {
        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamJpaRepository.save(teamA);
        teamJpaRepository.save(teamB);

        Member member1 = new Member("member1", 1, teamA);
        Member member2 = new Member("member2", 1, teamB);
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        //when
        memberRepository.findByIdAndAndTeamId(member1.getId(), member1.getTeam().getId());

        //then
    
    }
    
    @Test
    public void bulkUpdateTest() throws Exception {
        //given
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 19));
        memberJpaRepository.save(new Member("member3", 20));
        memberJpaRepository.save(new Member("member4", 21));
        memberJpaRepository.save(new Member("member5", 40));

        //when
        int resultCount = memberJpaRepository.bulkAgePlus(20);

        //then
        assertThat(resultCount).isEqualTo(3);
    
    }

}