package edu.tennis_scoreboard.controller;

import edu.tennis_scoreboard.dto.PlayerDto;
import edu.tennis_scoreboard.servicie.OngoingMatchesService;
import edu.tennis_scoreboard.util.StringValidator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "new-match", value = "/new-match")
public class NewMatchServlet extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ongoingMatchesService = (OngoingMatchesService) config
                .getServletContext()
                .getAttribute("ongoingMatchesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/view/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String playerOneName = req.getParameter("player1");
        String playerTwoName = req.getParameter("player2");
        StringValidator.checkValidPlayersName(playerOneName, playerTwoName);
        PlayerDto playerOne = ongoingMatchesService.getPlayerOrCreateNew(playerOneName);
        PlayerDto playerTwo = ongoingMatchesService.getPlayerOrCreateNew(playerTwoName);
        UUID matchId = ongoingMatchesService.addNewOngoingMatch(playerOne, playerTwo);
        resp.sendRedirect(String.format("%s?uuid=%s", req.getContextPath() + "/match-score", matchId));
    }
}
