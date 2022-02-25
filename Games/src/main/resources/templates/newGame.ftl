<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Начнем новую игру?</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="justify-content-start d-inline-flex">
        <form class="mt-2 ml-2" action="/logout" method="post">
            <button class="btn btn-danger d-flex justify-content-center" type="submit">Выйти из аккаунта</button>
        </form>
        <a class="btn btn-primary ml-2 mt-2 mr-2" href="/game/start">Новая игра</a>
    </div>

    <h2 class="text-dark ml-2">Поздравляю, ${congratulations}!!! </h2>
    <h2 class="text-dark ml-2">Число было угадано за ${step} раз(a). </h2>
    <h2 class="text-dark ml-2">Было сыграно ${numberOfGames} игр(a). </h2>
    <h2 class="text-dark ml-2">Твой рейтинг: ${avg}. </h2>
</body>
</html>