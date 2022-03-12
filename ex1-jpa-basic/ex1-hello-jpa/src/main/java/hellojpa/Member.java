package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "name")
	private String username;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
 	private Team team;

	public Member() { // JPA는 기본적으로 리플랙션을 사용하며 동적으로 쿼리를 생성하기에 기본 생성자가 꼭 필요하다.
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	// 양방향 연관관계의 편의 메서드 (한쪽에만 작성을 한다.) set 대신 이용
	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
