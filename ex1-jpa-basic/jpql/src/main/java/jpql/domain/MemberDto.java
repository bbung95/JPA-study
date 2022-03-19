package jpql.domain;

public class MemberDto {

    private String username;
    private int age;

    public String getUsername() {
        return username;
    }

    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
