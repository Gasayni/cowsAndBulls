package com.company;

// Правила игры: Программа задумывает строку из четырех разных цифр. Пример: 0123
// Человек пытается угадать это число путем ввода в консоль строки из 4-х цифр. Пример: 3521
// Программа выдает следующий результат.
// Если цифра есть в угадываемой строке и стоит на своем месте, то это "БЫК"
// Если цифра есть в угадываемой строке, но не на своем месте, то это "КОРОВА"
// Пример вывода программы: 2 коровы 1 бык.
// (окончания слов д.б. корректными, т.е. не д.б. "быка и 2 корова")

// Результат игры д. записываться в файл в следующем формате:
// Game №1 05.08.2021 6:46 Загаданная строка 0123
//      Запрос: 3521 Ответ: 2 коровы 1 бык
//      Запрос: 0134 Ответ: 1 корова 2 быка
//      ...
//      Строка была угадана за 5 попыток
// Game №2...

// При запуске программы заново. Из файла необходимо прочитать номер последней игры и при записи продолжить нумерацию
// * человек загадывает строку, а программа угадывает
// Ps. Постараться написать в стиле ООП

import java.text.ParseException;
import java.util.Scanner;

public class Game {

    //TODO static здесь наверное не нужен
    // и все переменные должны быть private
    public static String guessNum;
    public int countNumber = 4;
    public int numberGame;
    public int tryCount = 0;
    public void startGame() throws ParseException {
        // Прочитаем сначала, что есть в файле и вытащим номер последней игры
        numberGame = new ReadFile().methodRead();

        // пусть программа придумывает n-значное число
        guessNum = generateRandomNumber();

        // теперь можем записать новую игру
        new WriteFile().methodWrite(numberGame, guessNum);

        // Далее мы д. ввести n-значное число
        new InputClass().inputMethod();
    }

    public String generateRandomNumber() {
        StringBuilder n = new StringBuilder();
        // пока не придумаем все числа

        while ((n.length() != countNumber)) {
            // в условии цифры от 0 до 9 включительно
            int k = (int) (Math.random() * 10);
            // мы проверяем встречаются ли одинаковые числа
            if (!n.toString().contains(String.valueOf(k))) {
                n.append(k);
            }
        }
        return n.toString();
    }

    public void check(String inputLine) throws ParseException {
        // разделили на массив символов
        String[] inputNumMas = inputLine.split("");
        String[] guessNumMas = guessNum.split("");

        // Пример1: 0123 vs 3521
        // Пример2: 0123 vs 3111
        // Пример3: 0123 vs 1511
        // Пример3: 0123 vs 0111
        // теперь м. проверить каждый символ
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < inputNumMas.length; i++) {
            for (int j = 0; j < guessNumMas.length; j++) {
                if (inputNumMas[i].equals(guessNumMas[j])) {
                    if (i == j) bulls++;
                    else cows++;
                }
            }
        }
        // передаем на вывод полученных быков и коров
        new Main().printMethod(bulls, cows, inputLine);
    }

    public void replayGame() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Хотите сыграть еще раз?");
        System.out.println("Введите \"Да\" или \"Нет\"");
        String yesOrNo = input.next();
        if (yesOrNo.equals("Да") || yesOrNo.equals("да") || yesOrNo.equals("lf") || yesOrNo.equals("Lf")) {
            startGame();
        }
    }
}
