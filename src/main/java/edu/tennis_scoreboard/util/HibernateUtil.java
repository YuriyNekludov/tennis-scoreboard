package edu.tennis_scoreboard.util;

import edu.tennis_scoreboard.model.Match;
import edu.tennis_scoreboard.model.Player;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.lang.reflect.Proxy;

@UtilityClass
public class HibernateUtil {

    @Getter
    private SessionFactory sessionFactory;
    @Getter
    private Session session;

    void initSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperties(PropertyUtil.getSettings());
            configuration.addAnnotatedClass(Match.class);
            configuration.addAnnotatedClass(Player.class);
            configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void initProxySession() {
        session = (Session) Proxy.newProxyInstance(HibernateUtil.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
    }

    void closeSessionFactory() {
        sessionFactory.close();
    }
}
