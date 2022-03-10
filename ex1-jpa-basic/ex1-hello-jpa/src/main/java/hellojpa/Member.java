package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String username;
	
	public Member() { // JPA는 기본적으로 리플랙션을 사용하며 동적으로 쿼리를 생성하기에 기본 생성자가 꼭 필요하다.
	}
}
