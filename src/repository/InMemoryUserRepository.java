package repository;

import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 메모리 상에서 사용자 데이터를 관리하는 저장소 구현체.
 * 사용자를 리스트로 저장하고 조회 등의 기능을 제공.
 */
public class InMemoryUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(String userId) {
        return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
