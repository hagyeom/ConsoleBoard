package ui;

import java.util.Scanner;

/**
 * 콘솔에서 사용자 입력을 처리하는 클래스.
 * 문자열 및 정수 입력을 받는 기능을 제공.
 */
public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int getInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }
}
