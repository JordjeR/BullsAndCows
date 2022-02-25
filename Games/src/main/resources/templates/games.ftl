<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">

    <title>Быки и Коровы</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/static/js/check-inputs.js"></script>
</head>

<body>
      <button type="button" class="btn btn-primary mt-3 ml-3" data-toggle="modal" data-target="#rules">
          Правила игры
      </button>

      <div class="modal fade" id="rules" tabindex="-1" role="dialog" aria-labelledby="rule" aria-hidden="true">
          <div class="modal-dialog" role="document">
              <div class="modal-content">
                  <div class="modal-header">
                      <h5 class="modal-title" id="rule">Правила игры</h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                      </button>
                  </div>
                  <div class="modal-body">
                      <p class="text-info">
                          <p>Компьютер задумывает четыре различные цифры из 0,1,2,...9. </p>
                          <p>Игрок делает ходы, чтобы узнать эти цифры и их порядок. </p>
                          <p>Каждый ход состоит из четырёх цифр, 0 может стоять на первом месте. </p>
                          <p>В ответ компьютер показывает число отгаданных цифр, стоящих на своих местах
                             (число быков) и число отгаданных цифр, стоящих не на своих местах (число коров). </p>
                          <strong> Пример: </strong>
                          <br>
                          Компьютер задумал число
                          <strong> 0834. </strong>
                          <br>
                          Игрок сделал ход
                          <strong> 8134. </strong>
                          <br>
                          <br>
                          <strong>Компьютер ответил:</strong> <br>
                          2 быка (цифры 3 и 4) и 1 корова (цифра 8).
                      </p>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                  </div>
              </div>
          </div>
      </div>

      <h3 class="d-flex justify-content-center">Привет, ${user}!</h3>
      <br>

      <h3 class="text-left position-absolute ml-4">
          Рейтинг игроков
          <ol class="position-absolute mr-5">
              <#list usersInfo as usr>
                  <li class="text-dark small">${usr.username} - ${usr.average}</li>
              </#list>
          </ol>
      </h3>
      <div class="justify-content-start mb-4">
          <h3 class="text-center">
              Сыграем в игру - "Быки и Коровы"?
          </h3>
      </div>
      <span class="d-flex justify-content-center text-danger text-right" style="width: 92.5rem;">
              *число уже загадано компьютером -&nbsp;
              <#list random as r>
                  <p class="text-dark">${r}</p>
              </#list>
              &nbsp;(число выведено для проверки)
      </span>

      <div class="d-flex justify-content-center">
          <form class="form-inline d-flex justify-content-center" action="/game/add-rating" method="post">
              <label for="attempts" class="mr-sm-2">Поле для вашего хода:</label>
              <input id="attempts" type="text" class="form-control mr-1" onkeyup="check()"
                     name="attempts" minlength="4" maxlength="4" autocomplete="off" required>
              <button id="submit" type="submit" class="btn btn-primary" disabled>Сделать ход!</button>
          </form>
          <form class="form-inline" action="/logout" method="post">
              <button class="btn btn-danger d-flex justify-content-center ml-1 pl-4 pr-4" type="submit">Выйти</button>
          </form>
      </div>
      <#list all as el>
          <#if el.notice?contains("Введите")>
              <span class="text-danger d-flex justify-content-center">
              ${el.notice}
          </span>
          <#else>
              <span class="text-muted d-flex justify-content-center">
              ${el.notice}
          </span>
          </#if>
      </#list>
</body>
</html>