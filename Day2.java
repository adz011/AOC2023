import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 extends Solution {
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

    private boolean partOneIsViolated(String input, String keyword, int maxCubes) {
        Pattern pattern = Pattern.compile("\\d+ " + keyword);
        Matcher matcher = pattern.matcher(input);
        Pattern digitPattern = Pattern.compile("\\d+");
        while (matcher.find()) {
            Matcher digitMatcher = digitPattern.matcher(matcher.group());
            if (digitMatcher.find()) {
                if (Integer.parseInt(digitMatcher.group()) > maxCubes) {
                    return false;
                }
            } else return false;
        }
        return true;
    }

    private int partTwoHelper(String input, String keyword) {
        Pattern pattern = Pattern.compile("\\d+ " + keyword);
        Matcher matcher = pattern.matcher(input);
        Pattern digitPattern = Pattern.compile("\\d+");
        int biggestInt = 1;
        while (matcher.find()) {
            Matcher digitMatcher = digitPattern.matcher(matcher.group());
            if (digitMatcher.find()) {
                biggestInt = Math.max(biggestInt, Integer.parseInt(digitMatcher.group()));
            }
        }
        return biggestInt;
    }

    @Override
    public long partOne() {
        int index = 1, sum = 0;
        for (String s : splitLines) {
            if (partOneIsViolated(s, "green", 13) && partOneIsViolated(s, "red", 12) && partOneIsViolated(s, "blue", 14)) {
                sum += index;
            } else System.out.println("Violated index: " + index);

            index++;
        }
        return sum;
    }

    @Override
    public long partTwo() {
        int sum = 0;
        for (String s : splitLines) {
            sum += partTwoHelper(s, "green") * partTwoHelper(s, "red") * partTwoHelper(s, "blue");
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        new Day2().solve(Files.readString(FileSystems.getDefault().getPath("", "day2.txt")));
    }
}
