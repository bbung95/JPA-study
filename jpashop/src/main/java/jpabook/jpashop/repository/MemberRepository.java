package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    public List<Member> findAll(){
        List<Member> members =
                em.createQuery("select  m from Member m" , Member.class)
                        .getResultList();
        return members;
    }

    public List<Member> findByName(String name) {
        List<Member> members =
                em.createQuery("select m from Member m where m.name = :name" , Member.class)
                        .setParameter("name" , name)
                        .getResultList();
        return members;
    }

    public void delete(Member member){
        em.remove(member);
    }
}