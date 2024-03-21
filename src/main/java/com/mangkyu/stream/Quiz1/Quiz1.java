package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import lombok.var;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Quiz1 {

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream().map(csv -> csv[1].replace(" ", "").split(":"))
                .flatMap(Arrays::stream).collect(Collectors.toMap(Function.identity(), value -> 1, Integer::sum));
    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream().filter(csv -> csv[0].contains("정")).map(csv -> csv[1].replace(" ", "").split(":"))
                .flatMap(Arrays::stream).collect(Collectors.toMap(Function.identity(), value -> 1, Integer::sum));
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        int result = csvLines.stream().map(csv -> csv[2]).filter(str->str.contains("좋아"))
                .mapToInt(str -> str.split("좋아", -1).length -1).sum();

        return result;
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
