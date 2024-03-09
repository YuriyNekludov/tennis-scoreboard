package edu.tennis_scoreboard.util;

import edu.tennis_scoreboard.exception.NoValidParameterException;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class StringValidator {

    private final List<String> banWords;

    static {
        try (InputStream inputStream = StringValidator.class.getClassLoader().getResourceAsStream("banwords.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            banWords = reader.lines().toList();
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkValidPlayersName(String playerOneName, String playerTwoName) {
        if (containsBannedWords(playerOneName) || containsDigits(playerOneName)
        || containsBannedWords(playerTwoName) || containsDigits(playerTwoName)
        || playerOneName.toLowerCase().trim().equals(playerTwoName.trim().toLowerCase()))
            throw new NoValidParameterException();
    }

    private boolean containsBannedWords(String name) {
        for (String banWord : banWords) {
            if (banWord.equals(name.toLowerCase().trim()) || name.trim().toLowerCase().contains(banWord))
                return true;
        }
        return false;
    }

    private boolean containsDigits(String name) {
        Pattern pattern = Pattern.compile("\\d", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name.trim().toLowerCase());
        return matcher.find();
    }
}
