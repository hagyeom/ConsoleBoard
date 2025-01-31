package application;

import service.PostService;
import service.UserService;
import ui.ConsoleInput;
import ui.ConsoleOutput;
import ui.Menu;

/**
 * 콘솔 기반 커뮤니티 게시판 애플리케이션의 메인 클래스.
 * 프로그램 실행을 시작하고 사용자 입력에 따라 적절한 기능을 호출.
 */
public class CommunityApp {
    private PostService postService;
    private UserService userService;
    private Menu menu;
    private ConsoleInput input;
    private ConsoleOutput output;

    public CommunityApp(PostService postService, UserService userService, Menu menu, ConsoleInput input, ConsoleOutput output) {
        this.postService = postService;
        this.userService = userService;
        this.menu = menu;
        this.input = input;
        this.output = output;
    }

    public void run() {
        while (true) {
            menu.displayMainMenu();
            int choice = menu.getChoice(input);
            handleUserInput(choice);
        }
    }

    private void handleUserInput(int choice) {
        switch (choice) {
            case 1:
                // 게시글 목록 보기
                break;
            case 2:
                // 게시글 작성
                break;
            case 3:
                // 게시글 수정
                break;
            case 4:
                // 게시글 삭제
                break;
            case 5:
                // 로그인
                break;
            case 6:
                // 로그아웃
                break;
            case 7:
                // 종료
                System.exit(0);
                break;
            default:
                output.print("잘못된 선택입니다.");
        }
    }
}
