package application;

import entity.Post;
import entity.User;
import repository.InMemoryPostRepository;
import repository.InMemoryUserRepository;
import repository.PostRepository;
import repository.UserRepository;
import service.ApplicationState;
import service.PostService;
import service.UserService;
import ui.ConsoleInput;
import ui.ConsoleOutput;
import ui.Menu;

/**
 * 콘솔 기반 커뮤니티 게시판 애플리케이션의 메인 클래스.
 * - 프로그램 실행을 시작하고 사용자 입력에 따라 적절한 기능을 호출.
 * - 의존성 주입을 통해 서비스 계층과 연결.
 */
public class CommunityApp {
    // [핵심 의존성 객체]
    private PostService postService;      // 게시글 관련 비즈니스 로직 처리
    private UserService userService;      // 사용자 관련 비즈니스 로직 처리
    private Menu menu;                    // 콘솔 메뉴 출력 관리
    private ConsoleInput input;           // 사용자 입력 처리기
    private ConsoleOutput output;         // 콘솔 출력 처리기
    private ApplicationState applicationState; // 애플리케이션 상태 관리(로그인 정보 등)

    /**
     * 생성자: 의존성 주입을 통한 초기화
     *
     * @param postRepository 게시글 저장소 구현체
     * @param userRepository 사용자 저장소 구현체
     */
    public CommunityApp(PostRepository postRepository, UserRepository userRepository) {
        this.applicationState = new ApplicationState();
        this.postService = new PostService(postRepository);
        this.userService = new UserService(userRepository, applicationState);
        this.menu = new Menu();
        this.input = new ConsoleInput();
        this.output = new ConsoleOutput();
    }

    /**
     * 프로그램 메인 실행 루프
     * - 무한히 메뉴를 출력하고 사용자 입력 처리
     */
    public void run() {
        while (true) {
            menu.displayMainMenu();
            int choice = menu.getChoice(input);
            handleUserInput(choice);
        }
    }

    /**
     * 사용자 입력에 따른 기능 분기 처리
     *
     * @param choice 사용자가 선택한 메뉴 번호 (1~10)
     */
    private void handleUserInput(int choice) {
        switch (choice) {
            case 1:
                listPosts();
                break;       // 게시글 목록 보기
            case 2:
                viewPostDetail();
                break;  // 게시글 상세 조회
            case 3:
                addPost();
                break;         // 게시글 작성
            case 4:
                updatePost();
                break;      // 게시글 수정
            case 5:
                deletePost();
                break;      // 게시글 삭제
            case 6:
                registerUser();
                break;    // 회원가입
            case 7:
                login();
                break;           // 로그인
            case 8:
                logout();
                break;          // 로그아웃
            case 9:
                likePost();
                break;  // 좋아요 기능 추가
            case 10:
                shutdown();
                break;        // 프로그램 종료
            default:
                handleInvalidInput();    // 잘못된 입력 처리
        }
    }

    // [게시글 관련 기능] --------------------------------------------------

    /**
     * 게시글 목록 출력 (간략 정보)
     * - 모든 게시글의 ID, 제목, 작성자를 출력.
     */
    private void listPosts() {
        output.print("=== 게시글 목록 ===");
        postService.listPosts().forEach(post -> {
            output.print("ID: " + post.getPostId() + ", 제목: " + post.getTitle() + ", 작성자: " + post.getAuthor().getName());
        });
    }

    /**
     * 게시글 상세 조회 (모든 메타데이터 포함)
     * - 게시글 ID를 입력받아 상세 정보를 출력.
     * - 조회수 증가 기능 포함.
     */
    private void viewPostDetail() {
        int postId = input.getInt("조회할 게시글 ID: ");
        Post post = postService.viewPost(postId);

        if (post != null) {
            output.print("\n=== 게시글 상세 정보 ===");
            output.print("제목: " + post.getTitle());
            output.print("작성자: " + post.getAuthor().getName());
            output.print("작성일: " + post.getCreatedDate());
            output.print("조회수: " + post.getViews());
            output.print("좋아요: " + post.getLikes());
            output.print("내용:\n" + post.getContent() + "\n");
        } else {
            output.print("존재하지 않는 게시글입니다.");
        }
    }

    /**
     * 새 게시글 작성 (로그인 상태 필수)
     * - 로그인된 사용자가 게시글을 작성.
     */
    private void addPost() {
        if (!checkLoginState()) return;  // 로그인 상태 확인

        String title = input.getString("제목: ");
        String content = input.getString("내용: ");
        postService.addPost(title, content, applicationState.getLoggedInUser());
        output.print("게시글이 작성되었습니다.");
    }

    /**
     * 게시글 수정 (로그인 상태 필수)
     * - 게시글 ID를 입력받아 제목과 내용을 수정.
     */
    private void updatePost() {
        if (!checkLoginState()) return;  // 로그인 상태 확인

        int postId = input.getInt("수정할 게시글 ID: ");
        String newTitle = input.getString("새 제목: ");
        String newContent = input.getString("새 내용: ");
        if (postService.updatePost(postId, newTitle, newContent)) {
            output.print("게시글이 수정되었습니다.");
        } else {
            output.print("게시글 수정에 실패했습니다.");
        }
    }

    /**
     * 게시글 삭제 (로그인 상태 필수)
     * - 게시글 ID를 입력받아 삭제.
     */
    private void deletePost() {
        if (!checkLoginState()) return;  // 로그인 상태 확인

        int postId = input.getInt("삭제할 게시글 ID: ");
        if (postService.deletePost(postId)) {
            output.print("게시글이 삭제되었습니다.");
        } else {
            output.print("게시글 삭제에 실패했습니다.");
        }
    }

    // [사용자 관련 기능] --------------------------------------------------

    /**
     * 회원가입 처리 (중복 ID/이메일 체크 포함)
     * - 사용자 ID, 비밀번호, 이름, 이메일을 입력받아 회원가입.
     */
    private void registerUser() {
        String userId = input.getString("사용자 ID: ");
        String password = input.getString("비밀번호: ");
        String name = input.getString("이름: ");
        String email = input.getString("이메일(선택): ");

        // ID 중복 체크
        if (userService.findById(userId) != null) {
            output.print("[오류] 이미 존재하는 ID입니다.");
            return;
        }

        // 이메일 중복 체크 (입력된 경우만)
        if (email != null && !email.isEmpty() && userService.findByEmail(email) != null) {
            output.print("[오류] 이미 사용중인 이메일입니다.");
            return;
        }

        boolean result = userService.registerUser(userId, password, name, email);
        output.print(result ? "회원가입 성공!" : "회원가입 실패!");
    }

    /**
     * 로그인 처리
     * - 사용자 ID와 비밀번호를 입력받아 로그인.
     */
    private void login() {
        String userId = input.getString("사용자 ID: ");
        String password = input.getString("비밀번호: ");
        if (userService.login(userId, password)) {
            output.print("로그인 성공!");
        } else {
            output.print("로그인 실패!");
        }
    }

    /**
     * 로그아웃 처리
     * - 현재 로그인된 사용자를 로그아웃 상태로 변경.
     */
    private void logout() {
        userService.logout();
        output.print("로그아웃 되었습니다.");
    }

    /**
     * 게시글에 좋아요를 추가하는 메서드.
     * - 로그인 상태에서만 좋아요 가능.
     */
    private void likePost() {
        if (!checkLoginState()) return;  // 로그인 상태 확인

        int postId = input.getInt("좋아요를 누를 게시글 ID: ");
        if (postService.likePost(postId)) {
            output.print("좋아요를 눌렀습니다!");
        } else {
            output.print("게시글을 찾을 수 없습니다.");
        }
    }

    // [공통 유틸리티] -----------------------------------------------------

    /**
     * 로그인 상태 확인 공통 메서드
     *
     * @return 로그인 상태 (true: 로그인됨, false: 로그인 필요)
     */
    private boolean checkLoginState() {
        if (!applicationState.isLoggedIn()) {
            output.print("로그인이 필요합니다.");
            return false;
        }
        return true;
    }

    /**
     * 프로그램 정상 종료 처리
     * - 애플리케이션을 종료.
     */
    private void shutdown() {
        output.print("프로그램을 종료합니다.");
        System.exit(0);
    }

    /**
     * 잘못된 입력 처리
     * - 유효하지 않은 메뉴 선택 시 오류 메시지 출력.
     */
    private void handleInvalidInput() {
        output.print("잘못된 선택입니다.");
    }

    // [프로그램 시작점] ----------------------------------------------------

    /**
     * 프로그램 시작점
     * - 의존성 주입 및 애플리케이션 실행.
     */
    public static void main(String[] args) {
        // 의존성 주입: 저장소 구현체 생성
        PostRepository postRepository = new InMemoryPostRepository();
        UserRepository userRepository = new InMemoryUserRepository();

        // CommunityApp 인스턴스 생성 및 실행
        CommunityApp app = new CommunityApp(postRepository, userRepository);
        app.run();
    }
}