package service;

import entity.User;
import repository.UserRepository;
import repository.InMemoryUserRepository;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스.
 * 사용자 등록, 로그인, 로그아웃, 정보 수정 등의 기능을 제공.
 */
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(String userId, String password, String name, String email) {
        User user = new User(userId, password, name, email);
        userRepository.save(user);
        return true;
    }

    public boolean login(String userId, String password) {
        User user = userRepository.findById(userId);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public void logout() {
        // 로그아웃 처리
    }

    public User getLoggedInUser() {
        // 현재 로그인된 사용자 반환
        return null;
    }

    public boolean updateUser(String userId, String newPassword, String newName, String newEmail) {
        User user = userRepository.findById(userId);
        if (user != null) {
            user.updateUserInfo(newPassword, newName, newEmail);
            return true;
        }
        return false;
    }
}
