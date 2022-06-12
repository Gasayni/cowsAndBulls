package com.company;

// Правила игры: Программа задумывает строку из четырех разных цифр. Пример: 0123
// Человек пытается угадать это число путем ввода в консоль строки из 4х цифр. Пример: 3521
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

// При запуске программы занаво. Из файла необх. прочитать номер последней игры и при записи продолжить нумерацию
// * человек загадывает строку, а программа угадывает
// Ps. Постараться написать в стиле ООП


import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static String guessNum;
    public static int countNumber = 4;
    public static int numberGame;
    public static int tryCount = 0;


    public static void main(String[] args) throws ParseException {
        // Прочитаем сначала, что есть в файле и вытащим номер последней игры
        numberGame = new ReadFile().methodRead();

        // пусть программа придумывает n-значное число
        guessNum = myRandom();

        // теперь можем записать новую игру
        new WriteFile().methodWrite(numberGame, guessNum);

        // далее мы д. ввести n-значное число
        inputMethod();
    }

    public static String myRandom() {
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < countNumber; i++) {
            n.append((int) (Math.random() * 9));
        }
        return n.toString();
    }

    public static void inputMethod() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите " + countNumber + "х значное число");
        String inputLine = input.next();

        // нужно проверить на ввод херни
        if (checkInputStringMethod(inputLine)) {
            check(inputLine);
        }
    }

    public static boolean checkInputStringMethod(String s) {
        // проверяем на числа
        int n;
        try {
            n = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели что-то не то, попробуйте еще раз \n");
            inputMethod();
            return false;
        }
        // проверяем на кол-во цифр
        if (s.length() != countNumber) {
            System.out.println("Вы ввели что-то не то, попробуйте еще раз \n");
            inputMethod();
            return false;
        }
        return true;
    }

    public static void check(String inputLine) {
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
        printMethod(bulls, cows, inputLine);
    }

    public static String wordend(int n, String CowOrBull) {
        StringBuilder s = new StringBuilder();

        String wordCow = "Коров";
        String wordBull = "Бык";

        String cowEnd1 = "а";
        String cowEnd2 = "ы";
        String cowEnd3 = "";

        String bullEnd1 = "";
        String bullEnd2 = "а";
        String bullEnd3 = "ов";


        if (n % 10 == 1 && n % 100 != 11) {
            if (CowOrBull.equals("cows")) {
                s.append(wordCow);
                s.append(cowEnd1);
                return s.toString();
            } else {
                s.append(wordBull);
                s.append(bullEnd1);
                return s.toString();
            }
        } else if (n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20)) {
            if (CowOrBull.equals("cows")) {
                s.append(wordCow);
                s.append(cowEnd2);
                return s.toString();
            } else {
                s.append(wordBull);
                s.append(bullEnd2);
                return s.toString();
            }
        } else {
            if (CowOrBull.equals("cows")) {
                s.append(wordCow);
                s.append(cowEnd3);
                return s.toString();
            } else {
                s.append(wordBull);
                s.append(bullEnd3);
                return s.toString();
            }
        }
    }

    public static void printMethod(int bulls, int cows, String inputLine) {
        ReadFile readFile = new ReadFile();
        WriteFile writeFile = new WriteFile();
        StringBuilder reportLine = new StringBuilder();
        reportLine.append(readFile.readFile("reportGame.txt"));
        tryCount++;
        if (bulls == countNumber) {
            //      Строка была угадана за 5 попыток
            String lineReportWriteInFile = "\t Строка была угадана за " + tryCount + " попыток";
            // дописываем в файл
            reportLine.append(lineReportWriteInFile);
            System.out.println(lineReportWriteInFile);
            // записывается в файл

            writeFile.writeFile("reportGame.txt", reportLine.toString());
        } else {
            String lineReportWriteInFile = "\t Запрос: " + inputLine + " Ответ: " + cows + " "
                    + wordend(cows, "cows") + " " + bulls + " " + wordend(bulls, "bulls");

            System.out.println(lineReportWriteInFile);
            // дописываем в файл
            reportLine.append(lineReportWriteInFile);

            // записывается в файл
            writeFile.writeFile("reportGame.txt", reportLine.toString());

            // перебрасывает снова на следующую попытку
            inputMethod();
        }

    }


}