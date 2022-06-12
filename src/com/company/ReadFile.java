package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public String readFile(String pathFile) {
        StringBuilder finishTextFromFile = new StringBuilder();
        String readableLine;
        try {
            FileReader file = new FileReader(pathFile);
            BufferedReader br = new BufferedReader(file);
            while ((readableLine = br.readLine()) != null) {
                finishTextFromFile.append(readableLine).append("\n");
            }
            br.close();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return finishTextFromFile.toString();
    }
    public int numberGame = 0;

    public int methodRead() {
        // При запуске программы заново.
        // Из файла необх. прочитать номер последней игры и при записи продолжить нумерацию

        // Читаем все данные из файла
        String fileLine = readFile("reportGame.txt");
        // если файл не пустой
        if (fileLine.length() > 0) {
            // делим эту длиннющую строку по строкам, как в файле
            String[] fileLinesMas = fileLine.split("\n");
            // смотрим на все строки, начинающиеся на Game

            for (int i = 0; i<fileLinesMas.length; i++) {
                String[] laneMas = fileLinesMas[i].split(" ");
                if (laneMas[0].equals("Game")) {
                    numberGame = Integer.parseInt(laneMas[1].substring(1));
                }
            }
        }
        return numberGame;
    }
}
