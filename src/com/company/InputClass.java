package com.company;

import java.text.ParseException;
import java.util.Scanner;

public class InputClass {
    Game game = new Game();
    Scanner input = new Scanner(System.in);

    public void inputMethod() throws ParseException {
        System.out.println("Введите " + game.countNumber + "х значное число");
        String inputLine = input.next();

        // нужно проверить на ввод херни
        if (new Main().checkInputStringMethod(inputLine)) {
            game.check(inputLine);
        }
    }
}
