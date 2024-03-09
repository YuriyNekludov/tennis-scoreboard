package edu.tennis_scoreboard.controller;

import edu.tennis_scoreboard.dto.MatchDto;
import edu.tennis_scoreboard.exception.DataNotFoundException;
import edu.tennis_scoreboard.servicie.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "matches", value = "/matches")
public class MatchesServlet extends HttpServlet {

    private FinishedMatchesPersistenceService finishedMatchesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        finishedMatchesService = (FinishedMatchesPersistenceService) config
                .getServletContext()
                .getAttribute("finishedMatchesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("filter_by_player_name");
        String page = req.getParameter("page");
        int pageNumber = 1;
        List<MatchDto> matchList;
        if (page != null && !page.isEmpty())
            pageNumber = Integer.parseInt(page);
        if (name == null || name.isEmpty())
            matchList = finishedMatchesService.getAllMatches(pageNumber);
        else {
            matchList = finishedMatchesService.getMatchesByPlayerName(name, pageNumber);
            if (matchList.isEmpty())
                throw new DataNotFoundException();
        }
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("matchList", matchList);
        getServletContext()
                .getRequestDispatcher("/view/matches.jsp")
                .forward(req, resp);
    }
}
