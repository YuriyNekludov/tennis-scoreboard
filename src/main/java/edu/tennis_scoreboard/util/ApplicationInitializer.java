package edu.tennis_scoreboard.util;

import edu.tennis_scoreboard.mapper.MatchMapper;
import edu.tennis_scoreboard.mapper.PlayerMapper;
import edu.tennis_scoreboard.repository.MatchRepository;
import edu.tennis_scoreboard.repository.PlayerRepository;
import edu.tennis_scoreboard.servicie.FinishedMatchesPersistenceService;
import edu.tennis_scoreboard.servicie.MatchScoreCalculationService;
import edu.tennis_scoreboard.servicie.OngoingMatchesService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.Session;

import java.util.HashMap;

@WebListener
public class ApplicationInitializer implements ServletContextListener {

    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PropertyUtil.loadProperties();
        HibernateUtil.initSessionFactory();
        HibernateUtil.initProxySession();
        initFields();
        ScriptReader.initScript(HibernateUtil.getSession());
        ServletContext context = sce.getServletContext();
        context.setAttribute("ongoingMatchesService", ongoingMatchesService);
        context.setAttribute("matchScoreCalculationService", matchScoreCalculationService);
        context.setAttribute("finishedMatchesService", finishedMatchesService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.closeSessionFactory();
    }

    private void initFields() {
        Session session = HibernateUtil.getSession();
        PlayerRepository playerRepository = new PlayerRepository(session);
        MatchRepository matchRepository = new MatchRepository(session);
        ongoingMatchesService = new OngoingMatchesService(
                session,
                playerRepository,
                new HashMap<>(),
                new PlayerMapper());
        matchScoreCalculationService = new MatchScoreCalculationService();
        finishedMatchesService = new FinishedMatchesPersistenceService(
                session,
                matchRepository,
                playerRepository,
                new MatchMapper());
    }
}
