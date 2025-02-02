package ui;

import java.util.Scanner;

/**
 * 콘솔에서 사용자 입력을 처리하는 클래스.
 * 문자열 및 정수 입력을 받는 기능을 제공.
 */
public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);  // 입력 스캐너

    /**
     * 문자열 입력을 받는 메서드.
     *
     * @param prompt 입력 프롬프트 메시지
     * @return 사용자가 입력한 문자열
     */
    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * 정수 입력을 받는 메서드.
     *
     * @param prompt 입력 프롬프트 메시지
     * @return 사용자가 입력한 정수
     */
    public int getInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }
}