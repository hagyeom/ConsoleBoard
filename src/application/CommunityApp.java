package application;

import entity.User;
import repository.InMemoryPostRepository;
import repository.InMemoryUserRepository;
import service.ApplicationState;
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
    private ApplicationState applicationState;

    public CommunityApp() {
        InMemoryPostRepository postRepository = new InMemoryPostRepository();
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        this.applicationState = new ApplicationState();
        this.postService = new PostService(postRepository);
        this.userService = new UserService(userRepository, applicationState);
        this.menu = new Menu();
        this.input = new ConsoleInput();
        this.output = new ConsoleOutput();
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
                listPosts();
                break;
            case 2:
                addPost();
                break;
            case 3:
                updatePost();
                break;
            case 4:
                deletePost();
                break;
            case 5:
                login();
                break;
            case 6:
                logout();
                break;
            case 7:
                output.print("프로그램을 종료합니다.");
                System.exit(0);
                break;
            default:
                output.print("잘못된 선택입니다.");
        }
    }

    private void listPosts() {
        output.print("=== 게시글 목록 ===");
        postService.listPosts().forEach(post -> {
            output.print("ID: " + post.getPostId() + ", 제목: " + post.getTitle() + ", 작성자: " + post.getAuthor().getName());
        });
    }

    private void addPost() {
        if (!applicationState.isLoggedIn()) {
            output.print("로그인이 필요합니다.");
            return;
        }
        String title = input.getString("제목: ");
        String content = input.getString("내용: ");
        postService.addPost(title, content, applicationState.getLoggedInUser());
        output.print("게시글이 작성되었습니다.");
    }

    private void updatePost() {
        if (!applicationState.isLoggedIn()) {
            output.print("로그인이 필요합니다.");
            return;
        }
        int postId = input.getInt("수정할 게시글 ID: ");
        String newTitle = input.getString("새 제목: ");
        String newContent = input.getString("새 내용: ");
        if (postService.updatePost(postId, newTitle, newContent)) {
            output.print("게시글이 수정되었습니다.");
        } else {
            output.print("게시글 수정에 실패했습니다.");
        }
    }

    private void deletePost() {
        if (!applicationState.isLoggedIn()) {
            output.print("로그인이 필요합니다.");
            return;
        }
        int postId = input.getInt("삭제할 게시글 ID: ");
        if (postService.deletePost(postId)) {
            output.print("게시글이 삭제되었습니다.");
        } else {
            output.print("게시글 삭제에 실패했습니다.");
        }
    }

    private void login() {
        String userId = input.getString("사용자 ID: ");
        String password = input.getString("비밀번호: ");
        if (userService.login(userId, password)) {
            output.print("로그인 성공!");
        } else {
            output.print("로그인 실패!");
        }
    }

    private void logout() {
        userService.logout();
        output.print("로그아웃 되었습니다.");
    }

    public static void main(String[] args) {
        CommunityApp app = new CommunityApp();
        app.run();
    }
}