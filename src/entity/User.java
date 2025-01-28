package entity;

/**
 * 사용자 정보를 나타내는 데이터 모델 클래스.
 * 사용자의 ID, 비밀번호, 이름, 이메일 등을 관리.
 */
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

    // 생성자
    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    // Getter와 Setter 메서드
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    // 사용자 정보를 업데이트하는 메서드
    public void updateUserInfo(String newPassword, String newName, String newEmail) {
        this.password = newPassword;
        this.name = newName;
        this.email = newEmail;
    }
}
