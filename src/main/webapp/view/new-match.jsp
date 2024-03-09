<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

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

<main>
    <div class="form-container">
        <div class="form-wrapper">
            <div class="new-match-text-container">
                <p class="text-size">Для того чтобы создать матч, пожалуйста, введите имена игроков.</p>
            </div>
            <form action="new-match" method="post" class="form-input">
                <input type="text" name="player1" placeholder="Имя первого игрока" required>
                <input type="text" name="player2" placeholder="Имя второго игрока" required>
                <button type="submit" class="submit-button">Создать матч</button>
            </form>
        </div>
    </div>
</main>

<footer>
    <p>&copy; 2024 Created by Yuriy N.</p>
    <p>All rights reserved :)</p>
</footer>

</body>
</html>
