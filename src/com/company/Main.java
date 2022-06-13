package com.company;

import java.text.ParseException;

public class Main {
    static Game game = new Game();
    static InputClass inputClass = new InputClass();

    public static void main(String[] args) throws ParseException {
        game.startGame();
    }

    public boolean checkInputStringMethod(String s) throws ParseException {
        // проверяем на числа
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели что-то не то, попробуйте еще раз \n");
            inputClass.inputMethod();
            return false;
        }
        // проверяем на кол-во цифр
        if (s.length() != game.countNumber) {
            System.out.println("Вы ввели что-то не то, попробуйте еще раз \n");
            inputClass.inputMethod();
            return false;
        }
        return true;
    }

    public String wordEnd(int n, String CowOrBull) {
        StringBuilder s = new StringBuilder();

        String wordCow = "Коров";
        String cowEnd1 = "а";
        String cowEnd2 = "ы";
        String cowEnd3 = "";

        String wordBull = "Бык";
        String bullEnd1 = "";
        String bullEnd2 = "а";
        String bullEnd3 = "ов";

        String wordTry = "попыт";
        String tryEnd1 = "ку";
        String tryEnd2 = "ки";
        String tryEnd3 = "ок";

        if (n % 10 == 1 && n % 100 != 11) {
            if (CowOrBull.equals("cow")) {
                s.append(wordCow);
                s.append(cowEnd1);
                return s.toString();
            } else if (CowOrBull.equals("bull")){
                s.append(wordBull);
                s.append(bullEnd1);
                return s.toString();
            } else {
                s.append(wordTry);
                s.append(tryEnd1);
                return s.toString();
            }
        } else if (n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20)) {
            if (CowOrBull.equals("cow")) {
                s.append(wordCow);
                s.append(cowEnd2);
                return s.toString();
            } else if (CowOrBull.equals("bull")){
                s.append(wordBull);
                s.append(bullEnd2);
                return s.toString();
            } else {
                s.append(wordTry);
                s.append(tryEnd2);
                return s.toString();
            }
        } else {
            if (CowOrBull.equals("cow")) {
                s.append(wordCow);
                s.append(cowEnd3);
                return s.toString();
            } else if (CowOrBull.equals("bull")){
                s.append(wordBull);
                s.append(bullEnd3);
                return s.toString();
            } else {
                s.append(wordTry);
                s.append(tryEnd3);
                return s.toString();
            }
        }
    }

    public void printMethod(int bulls, int cows, String inputLine) throws ParseException {
        ReadFile readFile = new ReadFile();
        WriteFile writeFile = new WriteFile();
        StringBuilder reportLine = new StringBuilder();
        reportLine.append(readFile.readFile("reportGame.txt"));
        game.tryCount++;
        if (bulls == game.countNumber) {
            //      Строка была угадана за 5 попыток
            String lineReportWriteInFile = "\t Строка была угадана за " + game.tryCount + " "
                    + wordEnd(game.tryCount, "try");
            // дописываем в файл
            reportLine.append(lineReportWriteInFile);
            System.out.println(lineReportWriteInFile);
            // записывается в файл

            writeFile.writeFile("reportGame.txt", reportLine.toString());
            game.replayGame();
        } else {
            String lineReportWriteInFile = "\t Запрос: " + inputLine + " Ответ: " + cows + " "
                    + wordEnd(cows, "cow") + " " + bulls + " " + wordEnd(bulls, "bull");

            System.out.println(lineReportWriteInFile);
            // дописываем в файл
            reportLine.append(lineReportWriteInFile);

            // записывается в файл
            writeFile.writeFile("reportGame.txt", reportLine.toString());

            // перебрасывает снова на следующую попытку
            inputClass.inputMethod();
        }

    }


}
