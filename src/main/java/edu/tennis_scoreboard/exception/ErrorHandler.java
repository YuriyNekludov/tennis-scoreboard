package edu.tennis_scoreboard.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class ErrorHandler {

    public void handleError(HttpServletRequest req, HttpServletResponse resp, Throwable throwable) throws IOException, ServletException {
        int statusCode = determinateStatusCode(throwable);
        String errorMessage = determinateErrorMessage(throwable);
        resp.setStatus(statusCode);
        req.setAttribute("errorMessage", errorMessage);
        req.getRequestDispatcher("/view/error.jsp").forward(req, resp);
    }

    private int determinateStatusCode(Throwable throwable) {
        return switch (throwable.getClass().getSimpleName()) {
            case "InternalServerException", "NullPointerException" -> HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            case "NoValidParameterException" -> HttpServletResponse.SC_BAD_REQUEST;
            case "DataNotFoundException" -> HttpServletResponse.SC_NOT_FOUND;
            default -> throw new IllegalStateException("Unexpected value: " + throwable.getClass().getSimpleName());
        };
    }

    private String determinateErrorMessage(Throwable throwable) {
        return switch (throwable.getClass().getSimpleName()) {
            case "InternalServerException" -> """
                    Похоже, с сервером что-то не так. Нам нужно время, чтобы исправить проблему.
                    Пожалуйста, попробуйте снова через некоторое время.
                    """;
            case "NoValidParameterException" -> """
                    Видимо, вы решили создать матч с неправильными именами. Запомните:
                    1. Нельзя использовать маты и банворды в полях ввода имен игроков.
                    2. Игрок не может играть против себя же.
                    3. Вряд ли имена игроков могут содержать цифры.
                    Вернитесь назад и попробуйте снова.
                    """;
            case "DataNotFoundException" -> """
                    Данные о таком игроке отсутствуют. Попробуйте другое имя.
                    """;
            case "NullPointerException" -> """
                    Матч завершен. Пожалуйста, перейдите к списку завершенных матчей или создайте новый матч.
                    """;
            default -> """
                    Неизвестная ошибка. Пожалуйста, перейдите на главную страницу.
                    """;
        };
    }
}
