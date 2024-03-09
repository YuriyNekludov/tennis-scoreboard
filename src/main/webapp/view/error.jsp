<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String message = (String) request.getAttribute("errorMessage"); %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
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
    <div class="image-container">
        <img src="image/error.png" alt="еррор">
    </div>
    <section class="main-section">
        <div class="text-container">
            <p class="text-size">
                <%= String.valueOf(message) %>
            </p>
            <p style="text-align: center">Перейти<a class="main-a" href="new-match"> к созданию нового матча </a>,<a
                    class="main-a" href="matches">
                к списку завершенных матчей</a> или <a class="main-a" href="main">на главную страницу</a>?</p>
        </div>
    </section>
</main>

<footer>
    <p>&copy; 2024 Created by Yuriy N.</p>
    <p>All rights reserved :)</p>
</footer>

</body>
</html>