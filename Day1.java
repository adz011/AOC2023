import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 extends Solution {

    List<String> splitLines;

    @Override
    public void handleInput(String input) {
        Pattern pattern = Pattern.compile(".+");
        Matcher matcher = pattern.matcher(input);
        splitLines = new ArrayList<>();
        while (matcher.find()) {
            splitLines.add(matcher.group());
        }
    }

    @Override
    public long partOne() {
        Pattern pattern = Pattern.compile("\\d");
        int sum = 0;
        for (String s : splitLines) {
            Matcher matcher = pattern.matcher(s);
            String firstDigit = "";
            if (matcher.find()) {
                firstDigit = matcher.group();
            }
            String lastDigit = firstDigit;
            while (matcher.find()) {
                lastDigit = matcher.group();
            }
            sum += Integer.parseInt(firstDigit + lastDigit);
        }
        return sum;
    }

    @Override
    public long partTwo() {
        Pattern pattern = Pattern.compile("(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)|\\d");
        long sum = 0;
        for (String s : splitLines) {
            Matcher matcher = pattern.matcher(s);
            String firstDigit = "";
            if (matcher.find()) {
                firstDigit = matcher.group();
            }
            String lastDigit = firstDigit;
            for (int i = matcher.end() - 1; i < s.length(); i++) {
                if(matcher.find(i)){
                    lastDigit = matcher.group();
                }
            }
            System.out.print(Integer.parseInt(partTwoHelper(firstDigit) + partTwoHelper(lastDigit)));
            sum += Long.parseLong(partTwoHelper(firstDigit) + partTwoHelper(lastDigit));
        }
        return sum;
    }

    private String partTwoHelper(String s) {
        return switch (s) {
            case "one" -> "1";
            case "two" -> "2";
            case "three" -> "3";
            case "four" -> "4";
            case "five" -> "5";
            case "six" -> "6";
            case "seven" -> "7";
            case "eight" -> "8";
            case "nine" -> "9";
            default -> s;
        };
    }

    public static void main(String[] args) throws IOException {
        new Day1().solve(Files.readString(FileSystems.getDefault().getPath("", "day1.txt")));
    }
}
