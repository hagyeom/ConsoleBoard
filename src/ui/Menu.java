package ui;

/**
 * 콘솔 기반 메뉴를 관리하는 클래스.
 * 메인 메뉴를 출력하고 사용자 선택을 받는 기능을 제공.
 */
public class Menu {
    /**
     * 메인 메뉴를 출력하는 메서드.
     */
    public void displayMainMenu() {
        System.out.println("1. 게시글 목록 보기");
        System.out.println("2. 게시글 상세 조회");
        System.out.println("3. 게시글 작성");
        System.out.println("4. 게시글 수정");
        System.out.println("5. 게시글 삭제");
        System.out.println("6. 회원가입");
        System.out.println("7. 로그인");
        System.out.println("8. 로그아웃");
        System.out.println("9. 게시글 좋아요");
        System.out.println("10. 종료");
    }

    /**
     * 사용자 선택을 입력받는 메서드.
     *
     * @param input 콘솔 입력 객체
     * @return 사용자가 선택한 메뉴 번호
     */
    public int getChoice(ConsoleInput input) {
        return input.getInt("선택: ");
    }
}