<html>
<head>
    <title>Регистрация</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
    <body>
        <div class="container">
            <h2>Регистрация</h2>

            <form action="/register" method="post">
                <div class="form-group">
                    <label for="username">Имя пользователя</label>
                    <input type="text" class="form-control" id="username" placeholder="Введите имя пользователя" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input type="password" class="form-control" id="password" placeholder="Введите пароль" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                <a href="/auth/login" type="href" class="btn btn-link">Назад</a>
            </form>
        </div>
    </body>
</html>