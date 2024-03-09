package edu.tennis_scoreboard.util;

import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@UtilityClass
public class ScriptReader {

    public void initScript(Session session) {
        try {
            session.beginTransaction();
            session.createNativeQuery(readScriptFromFile("init_db.sql")).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String readScriptFromFile(String fileName) {
        try (InputStream inputStream = ScriptReader.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
