package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    //TODO хорошее название метода
    public String readFile(String pathFile) {
        StringBuilder finishTextFromFile = new StringBuilder();
        String readableLine;
        try {
            FileReader file = new FileReader(pathFile);
            BufferedReader br = new BufferedReader(file);
            //TODO у класса BufferedReader есть метод lines, можно получить все содержимое
            // в виде списка строк br.lines().collect(Collectors.toList()) (некритично)
            while ((readableLine = br.readLine()) != null) {
                finishTextFromFile.append(readableLine).append("\n");
            }
            //TODO чтобы здесь не закрывать вручную, можно использовать конструкцию
            // try with resources
            br.close();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return finishTextFromFile.toString();
    }

    public int numberGame = 0;

    //TODO метод возвращает номер последней игры. Может назвать его так, чтобы было понятно, что он делает?
    // например getLastGameNumber или getNumberOfLastGame
    public int methodRead() {
        // При запуске программы заново.
        // Из файла необходимо прочитать номер последней игры и при записи продолжить нумерацию

        // Читаем все данные из файла
        String fileLine = readFile("reportGame.txt");
        // если файл не пустой
        if (fileLine.length() > 0) {
            // делим эту длиннющую строку по строкам, как в файле
            String[] fileLinesMas = fileLine.split("\n");
            // смотрим на все строки, начинающиеся на Game

            //TODO может лучше искать с конца, найти первую строку, которая начинается на Game
            // и вытащить оттуда номер
            for (String fileLinesMa : fileLinesMas) {
                String[] laneMas = fileLinesMa.split(" ");
                if (laneMas[0].equals("Game")) {
                    numberGame = Integer.parseInt(laneMas[1].substring(1));
                }
            }
        }
        return numberGame;
    }
}
