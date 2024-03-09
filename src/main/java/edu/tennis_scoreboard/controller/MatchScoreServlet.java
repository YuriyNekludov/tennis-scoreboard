package edu.tennis_scoreboard.controller;

import edu.tennis_scoreboard.model.ongoing_match.OngoingMatch;
import edu.tennis_scoreboard.model.ongoing_match.State;
import edu.tennis_scoreboard.servicie.FinishedMatchesPersistenceService;
import edu.tennis_scoreboard.servicie.MatchScoreCalculationService;
import edu.tennis_scoreboard.servicie.OngoingMatchesService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "match-score", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ongoingMatchesService = (OngoingMatchesService) config
                .getServletContext()
                .getAttribute("ongoingMatchesService");
        matchScoreCalculationService = (MatchScoreCalculationService) config
                .getServletContext()
                .getAttribute("matchScoreCalculationService");
        finishedMatchesService = (FinishedMatchesPersistenceService) config
                .getServletContext()
                .getAttribute("finishedMatchesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchUuid = UUID.fromString(req.getParameter("uuid"));
        OngoingMatch ongoingMatch = ongoingMatchesService.getOngoingMatchByUuid(matchUuid);
        req.setAttribute("match", ongoingMatch);
        if (ongoingMatch.getState() != State.ON_GOING) {
            ongoingMatch.setWinnerId();
            finishedMatchesService.saveFinishedMatch(ongoingMatch);
            getServletContext()
                    .getRequestDispatcher("/view/match-score.jsp")
                    .forward(req, resp);
            ongoingMatchesService.deleteFinishedMatch(matchUuid);
        } else {
            getServletContext()
                    .getRequestDispatcher("/view/match-score.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long playerId = Long.parseLong(req.getParameter("playerId"));
        UUID matchUuid = UUID.fromString(req.getParameter("uuid"));
        OngoingMatch ongoingMatch = ongoingMatchesService.getOngoingMatchByUuid(matchUuid);
        matchScoreCalculationService.calculate(ongoingMatch, playerId);
        resp.sendRedirect(String.format("%s?uuid=%s", req.getContextPath() + "/match-score", ongoingMatch.getId()));
    }
}
