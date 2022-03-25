package jpabook.jpashop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    public Member() {
    }

    public Member(Long id, String username) {
        this.id = id;
        this.name = username;
    }
}
