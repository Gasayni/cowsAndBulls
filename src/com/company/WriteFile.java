package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteFile {
    public void writeFile(String path, String writeLine) {
        //        Записываем строку в файл
        try (FileWriter writer = new FileWriter(path, false)) {
            // запись всей строки
            //TODO для чего эта строчка?
            new FileWriter(path, false).close();
            writer.write(writeLine);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void methodWrite(int numberGame, String guessNum) {

        //TODO это жестко, ты считываешь все содержимое файла, а потом записываешь обратно
        // посмотри за что отвечает второй параметр в объявлении FileWriter writer = new FileWriter(path, false)
        ReadFile readFile = new ReadFile();
        StringBuilder reportLine = new StringBuilder();
        reportLine.append(readFile.readFile("reportGame.txt"));
        // теперь можем записать новую игру
        // Game №1 05.08.2021 6:46 Загаданная строка 0123
        //TODO постарайся не использовать Date (некритично)
        // лучше использовать LocalDate
        Date dateNow = new Date();
        SimpleDateFormat date = new SimpleDateFormat("d.MM.y H:m");
        numberGame++;
        String lineReportWriteInFile =
                "Game №" + numberGame + " " + date.format(dateNow) + " Загаданная строка " + guessNum;
        reportLine.append(lineReportWriteInFile);

        writeFile("reportGame.txt", reportLine.toString());
    }
}
