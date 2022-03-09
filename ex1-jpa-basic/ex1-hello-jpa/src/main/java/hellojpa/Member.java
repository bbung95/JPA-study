package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
	
	@Id
	private long id;
	private String name;
	
	public Member() { // JPA는 기본적으로 리플랙션을 사용하며 동적으로 쿼리를 생성하기에 기본 생성자가 꼭 필요하다.
	}
	
	public Member(long id , String name)	 {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
