package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteFile {
    public void writeFile(String path, String writeLine) {
        //        Записываем строку в файл
        try (
                FileWriter writer = new FileWriter(path, false)) {
            // запись всей строки
            new FileWriter(path, false).close();
            writer.write(writeLine);
            writer.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void methodWrite(int numberGame, String guessNum) throws ParseException {
        ReadFile readFile = new ReadFile();
        StringBuilder reportLine = new StringBuilder();
        reportLine.append(readFile.readFile("reportGame.txt"));
        // теперь можем записать новую игру
        // Game №1 05.08.2021 6:46 Загаданная строка 0123
        Date dateNow = new Date();
        SimpleDateFormat date = new SimpleDateFormat("d.MM.y H:m");
        numberGame++;
        String lineReportWriteInFile = "Game №" + numberGame + " " + date.format(dateNow) + " Загаданная строка " + guessNum;
        reportLine.append(lineReportWriteInFile);

        writeFile("reportGame.txt", reportLine.toString());
    }
}
