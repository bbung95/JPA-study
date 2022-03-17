package hellojpa;

import hellojpa.embeded.Address;
import hellojpa.embeded.Period;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "name")
	private String username;

	@Embedded
	private Period period;

	@Embedded
	private Address adress;

	@ElementCollection
	@CollectionTable(name = "FAVORITE_FOOD" ,  joinColumns =
		@JoinColumn( name = "MEMBER_ID"))
	@Column(name = "FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<>();

	@ElementCollection
	@CollectionTable(name = "ADDRESS" , joinColumns =
		@JoinColumn( name = "MEMBER_ID")
	)
	private List<Address> addressHistory = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY) // 프록시 객체 반환 지연로딩
	@JoinColumn(name = "TEAM_ID")
 	private Team team;

	@ManyToMany
	@JoinTable(name = "MEMBER_PRODUCT")
	private List<Product> products = new ArrayList<>();

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

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
