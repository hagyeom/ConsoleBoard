package service;

import entity.User;

/**
 * 애플리케이션의 현재 상태를 관리하는 클래스.
 * 현재 로그인된 사용자 정보를 저장하고 관리.
 */
public class ApplicationState {
    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
}
