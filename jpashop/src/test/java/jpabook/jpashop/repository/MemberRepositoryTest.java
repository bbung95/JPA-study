package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // Test 코드에 있는 Transaction은 완료후 롤백
    @Rollback(value = false)
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setName("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMamber = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMamber.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMamber.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMamber).isEqualTo(member); // 영속성 컨텍스트 1차캐시 값
    }

}