package repository;

import entity.User;

import java.util.List;

/**
 * 사용자 데이터를 관리하는 저장소 인터페이스.
 * 사용자의 저장, 조회 등의 기능을 정의.
 */
public interface UserRepository {
    void save(User user);

    List<User> findAll();

    User findById(String userId);

    User findByEmail(String email);
}
