package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    //@Autowired // 필드 주입 어노테이션보다 생성자 어노테이션 추천
    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional(readOnly = false)
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // Exception
        List<Member> members = memberRepository.findByName(member.getName());
        if(!members.isEmpty()){
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMember(){

        List<Member> members = memberRepository.findAll();
        return members;
    }

    // 회원 상세조회
    public Member findOne(Long id){

        Member member = memberRepository.findById(id);
        return member;
    }
}
