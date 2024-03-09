<%@ page import="edu.tennis_scoreboard.model.ongoing_match.OngoingMatch" %>
<%@ page import="edu.tennis_scoreboard.model.ongoing_match.State" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <% OngoingMatch match = (OngoingMatch) request.getAttribute("match"); %>
</head>
<body>

<header>
    <h1>Табло теннисного матча</h1>
</header>

<nav class="navigation">
    <a href="main" class="nav-link">Главная страница</a>
    <a href="new-match" class="nav-link">Новый матч</a>
    <a href="matches" class="nav-link">Список матчей</a>
</nav>
<div class="form-container-score">
    <table class="match-table">
        <thead>

        <% if (match.getMatchScore().getSetStage().getDisputeStage() != null && match.getState() == State.ON_GOING) {%>
        <tr>
            <th colspan="6">Начался ТАЙ-БРЕЙК</th>
        </tr>
        <% } else if (match.getMatchScore().getSetStage().getGameStage().getDisputeStage() != null && match.getState() == State.ON_GOING) {%>
        <tr>
            <th colspan="6">Начался счет РОВНО</th>
        </tr>
        <% } else if (match.getState() != State.ON_GOING) {%>
        <tr>
            <th colspan="4">Матч завершен! Победу в этом матче одержал:
                <% if (match.getState() == State.PLAYER_ONE_WIN) { %>
                <%= match.getPlayerOneName() %>
                <% } else { %>
                <%= match.getPlayerTwoName() %>
                <% } %>
                <form action="matches" method="get">
                    <button class="submit-button" type="submit">Перейти к списку матчей</button>
                </form>
            </th>
        </tr>
        <% } %>

        <tr>
            <th>Имя игрока</th>
            <th>Сеты</th>
            <th>Геймы</th>
            <% if (match.getMatchScore().getSetStage().getDisputeStage() != null) { %>
            <th>Тай-брейк очки</th>
            <% } else { %>
            <th>Очки</th>
            <% } %>
            <% if (match.getState() == State.ON_GOING) { %>
            <th colspan="2">Действия</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%= match.getPlayerOneName() %>
            </td>
            <td><%= match.getMatchScore().getPlayersMatchScore().get(match.getPlayerOneId()) %>
            </td>
            <td><%= match.getMatchScore().getSetStage().printScore(match.getPlayerOneId()) %>
            </td>
            <% if (match.getMatchScore().getSetStage().getDisputeStage() != null) { %>
            <td><%= match.getMatchScore().getSetStage().getDisputeStage().getPlayersScore().get(match.getPlayerOneId()) %>
            </td>
            <% } else { %>
            <td><%= match.getMatchScore().getSetStage().getGameStage().printScore(match.getPlayerOneId())%>
            </td>
            <% } %>
            <% if (match.getState() == State.ON_GOING) {%>
            <td>
                <form action="<%=request.getContextPath()%>/match-score?uuid=<%=match.getId()%>" method="post">
                    <input type="hidden" name="playerId" value=<%=match.getPlayerOneId()%>>
                    <button class="submit-button" type="submit"><%= match.getPlayerOneName() %> выиграл очко</button>
                </form>
            </td>
            <% } %>

        </tr>
        <tr>
            <td><%= match.getPlayerTwoName() %>
            </td>
            <td><%= match.getMatchScore().getPlayersMatchScore().get(match.getPlayerTwoId()) %>
            </td>
            <td><%= match.getMatchScore().getSetStage().printScore(match.getPlayerTwoId()) %>
            </td>
            <% if (match.getMatchScore().getSetStage().getDisputeStage() != null) { %>
            <td><%= match.getMatchScore().getSetStage().getDisputeStage().getPlayersScore().get(match.getPlayerTwoId()) %>
            </td>
            <% } else { %>
            <td><%= match.getMatchScore().getSetStage().getGameStage().printScore(match.getPlayerTwoId())%>
            </td>
            <% } %>
            <% if (match.getState() == State.ON_GOING) {%>
            <td>
                <form action="<%=request.getContextPath()%>/match-score?uuid=<%=match.getId()%>" method="post">
                    <input type="hidden" name="playerId" value=<%=match.getPlayerTwoId()%>>
                    <button type="submit" class="submit-button"><%= match.getPlayerTwoName() %> выиграл очко</button>
                </form>
            </td>
            <% } %>
        </tr>
        </tbody>
    </table>
</div>
<footer>
    <p>&copy; 2024 Created by Yuriy N.</p>
    <p>All rights reserved :)</p>
</footer>
</body>
</html>
