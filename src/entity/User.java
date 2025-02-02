package entity;

/**
 * 사용자 정보를 나타내는 데이터 모델 클래스.
 * 사용자의 ID, 비밀번호, 이름, 이메일 등을 관리.
 */
public class User {
    private String userId;       // 사용자의 고유 ID
    private String password;     // 사용자의 비밀번호
    private String name;         // 사용자의 이름
    private String email;        // 사용자의 이메일 주소 (선택 사항)

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

    public void setPassword(String password) {
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

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 사용자 정보를 업데이트하는 메서드.
     *
     * @param newPassword 새로운 비밀번호
     * @param newName     새로운 이름
     * @param newEmail    새로운 이메일
     */
    public void updateUserInfo(String newPassword, String newName, String newEmail) {
        this.password = newPassword;
        this.name = newName;
        this.email = newEmail;
    }
}