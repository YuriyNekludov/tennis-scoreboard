<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="edu.tennis_scoreboard.dto.MatchDto" %>
<%@ page import="java.util.List" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matches</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <% List<MatchDto> matchList = (List<MatchDto>) request.getAttribute("matchList"); %>
</head>
<body>

<header>
    <h1>Табло теннисного матча</h1>
</header>

<nav class="navigation">
        <form class="search-form" action="matches" method="get">
            <input type="text" name="filter_by_player_name" placeholder="Поиск матчей по имени игрока">
            <button type="submit" class="submit-button">Найти</button>
        </form>
    <a href="main" class="nav-link">Главная страница</a>
    <a href="new-match" class="nav-link">Новый матч</a>
    <a href="matches" class="nav-link">Список матчей</a>
</nav>
<div class="matches-form-container">
    <table class="match-table">
        <thead>

        <tr>
            <th>Имя первого игрока</th>
            <th>Имя второго игрока</th>
            <th>Победитель</th>
        </tr>
        </thead>

        <tbody>
        <% for (MatchDto match : matchList) { %>
        <tr>
            <td><a class="main-a"
                   href="<%=request.getContextPath()%>/matches?filter_by_player_name=<%= match.playerOneName() %>"><%= match.playerOneName() %>
            </a>
            </td>
            <td><a class="main-a"
                   href="<%=request.getContextPath()%>/matches?filter_by_player_name=<%= match.playerTwoName() %>"><%= match.playerTwoName() %>
            </a>
            </td>
            <td><a class="main-a"
                   href="<%=request.getContextPath()%>/matches?filter_by_player_name=<%= match.winnerName() %>"><%= match.winnerName() %>
            </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<div class="matches-text-container">
        <%
            int pageNumber = (int) request.getAttribute("pageNumber");
        %>
        <% if (pageNumber > 1) { %>
        <form action="matches" method="get">
            <button class="submit-button" type="submit" name="page" value= <%= pageNumber - 1 %>><%= pageNumber - 1 %>
            </button>
        </form>
        <%= pageNumber %>
        <% if (matchList.size() == 15) { %>
        <form action="matches" method="get">
            <button class="submit-button" type="submit" name="page" value= <%= pageNumber + 1 %>><%= pageNumber + 1 %>
            </button>
        </form>
        <% } %>
        <% } else { %>
        <%= pageNumber %>
        <% if (matchList.size() == 15) { %>
        <form action="matches" method="get">
            <button class="submit-button" type="submit" name="page" value= <%= pageNumber + 1 %>><%= pageNumber + 1 %>
            </button>
        </form>
        <% } %>
        <% } %>
    </div>
<footer>
    <p>&copy; 2024 Created by Yuriy N.</p>
    <p>All rights reserved :)</p>
</footer>
</body>
</html>