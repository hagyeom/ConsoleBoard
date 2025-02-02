package service;

import entity.User;

/**
 * 애플리케이션의 현재 상태를 관리하는 클래스.
 * 현재 로그인된 사용자 정보를 저장하고 관리.
 */
public class ApplicationState {
    private User loggedInUser;  // 현재 로그인된 사용자

    /**
     * 현재 로그인된 사용자를 반환하는 메서드.
     *
     * @return 현재 로그인된 사용자 객체 (없으면 null 반환)
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * 로그인된 사용자를 설정하는 메서드.
     *
     * @param user 로그인할 사용자 객체
     */
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    /**
     * 현재 로그인 상태를 확인하는 메서드.
     *
     * @return 로그인 상태 (true: 로그인됨, false: 로그아웃됨)
     */
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
}