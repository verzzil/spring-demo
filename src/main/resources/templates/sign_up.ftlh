<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/singUp.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="../js/main.js"></script>
</head>
<body>
<div id="layer5" style="left: 0 !important;">
    <a href="/" id="goToMain"><img src="../images/goToMain.png" height="100" alt=""></a>
    <form action="/signUp" method="post" id="signUpForm">
        <h1>Registration</h1>
        <input name="firstName" type="text"
               placeholder="Введите имя">
        <input name="lastName" type="text"
               placeholder="Введите фамилию">
        <input name="email" type="email"
               placeholder="Введите email">
        <input name="age" type="number"
               placeholder="Введите Ваш возраст">
        <input name="city" type="text" placeholder="Введите ваш город">
        <input name="password" type="password"
               placeholder="Enter password">
        <label for="gender" style="text-align: center; color: #fff;">Выберете пол: </label>
        <select name="gender" id="gender">
            <option value="М">Мужской</option>
            <option value="Ж">Женский</option>
        </select>
        <input type="submit" value="Регистрация">
    </form>

    <div id="layers">
        <div id="layer1"></div>
        <div id="layer2"></div>
        <div id="layer3"></div>
        <div id="layer4"></div>
        <div id="layer5">
            <img src="../images/close.png" id="closeSignInForm" alt="">
            <form id="signInForm" action="/" method="post">
                <h1>Log in</h1>
                <input type="text" name="email" autocomplete="none" placeholder="Enter email">
                <input type="password" name="password" placeholder="Enter password">
                <input type="hidden" name="sign-in">
                <input type="submit" value="Log in">
            </form>
            <ul id="layerSprite">
                <li><img src="../images/greeting/choco.png" width="200" alt=""></li>
                <li><img src="../images/greeting/home.png" width="200" alt=""></li>
                <li style="justify-self: end;"><img src="../images/greeting/choco.png" width="200" alt=""></li>
                <li style="justify-self: end;"><img src="../images/greeting/home.png" width="200" alt=""></li>
            </ul>
            <h3 style="z-index: 20; color: #fff;">If you don't have an account, you can <a style="color: #fff"
                                                                                           href="/signUp">register</a>
            </h3>
        </div>
    </div>

</div>

</body>
</html>