package service;

import entity.User;
import repository.UserRepository;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스.
 * 사용자 등록, 로그인, 로그아웃, 정보 수정 등의 기능을 제공.
 */
public class UserService {
    private UserRepository userRepository;      // 사용자 저장소 인터페이스
    private ApplicationState applicationState;  // 애플리케이션 상태 관리

    public UserService(UserRepository userRepository, ApplicationState applicationState) {
        this.userRepository = userRepository;
        this.applicationState = applicationState;
    }

    /**
     * 새로운 사용자를 등록하는 메서드.
     *
     * @param userId   사용자 ID
     * @param password 비밀번호
     * @param name     이름
     * @param email    이메일
     * @return 등록 성공 여부 (항상 true 반환)
     */
    public boolean registerUser(String userId, String password, String name, String email) {
        User user = new User(userId, password, name, email);  // 새로운 사용자 생성
        userRepository.save(user);  // 저장소에 사용자 저장
        return true;
    }

    /**
     * 사용자 로그인을 처리하는 메서드.
     *
     * @param userId   사용자 ID
     * @param password 비밀번호
     * @return 로그인 성공 여부 (true: 성공, false: 실패)
     */
    public boolean login(String userId, String password) {
        User user = userRepository.findById(userId);
        if (user != null && user.getPassword().equals(password)) {
            applicationState.setLoggedInUser(user);  // 로그인 상태 설정
            return true;
        }
        return false;
    }

    /**
     * 사용자 로그아웃을 처리하는 메서드.
     */
    public void logout() {
        applicationState.setLoggedInUser(null);  // 로그인 상태 해제
    }

    /**
     * 현재 로그인된 사용자를 반환하는 메서드.
     *
     * @return 현재 로그인된 사용자 객체 (없으면 null 반환)
     */
    public User getLoggedInUser() {
        return applicationState.getLoggedInUser();
    }

    /**
     * 사용자 정보를 수정하는 메서드.
     *
     * @param userId      사용자 ID
     * @param newPassword 새로운 비밀번호
     * @param newName     새로운 이름
     * @param newEmail    새로운 이메일
     * @return 수정 성공 여부 (true: 성공, false: 실패)
     */
    public boolean updateUser(String userId, String newPassword, String newName, String newEmail) {
        User user = userRepository.findById(userId);
        if (user != null) {
            user.updateUserInfo(newPassword, newName, newEmail);  // 사용자 정보 업데이트
            return true;
        }
        return false;
    }
}