<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find yourself</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Bungee+Shade&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="/js/main.js"></script>
    <script src="/js/submitForms.js"></script>
</head>
<body>

<form action="/" method="post" id="filters">
    <input type="hidden" name="filter">
    <h1 style="grid-column-start: 1; grid-column-end: 4;">Filters</h1>
    <label for="gender-option">Пол:</label>
    <select name="gender" id="gender-option" style="grid-column-start: 2; grid-column-end: 4;">
        <option value="any-gender" selected>Любой</option>
        <option value="М">Мужской</option>
        <option value="Ж">Женский</option>
    </select>
    <label for="from_age">Возраст:</label>
    <input name="from-age" type="number" id="from_age" placeholder="От">
    <input name="to-age" type="number" id="to_age" placeholder="До">
    <label for="city-option">Город:</label>
    <select name="city" id="city-option" style="margin-bottom:10px;grid-column-start: 2; grid-column-end: 4;">
        <option value="any-city" selected>Любой</option>
        <option value="Уфа">Уфа</option>
        <option value="Казань">Казань</option>
        <option value="Стерлитамак">Стерлитамак</option>
        <option value="Салават">Салават</option>
        <option value="Нурмина">Нурмина</option>
        <option value="Ереван">Ереван</option>
    </select>

    <input type="submit" id="submit-search-user-by-criteria" style="grid-column-start: 1; grid-column-end: 4;"/>
</form>

<#--<jsp:include page="nav.jsp"/>-->
<nav>
    <div id="signIn">
        <img src="./images/signin.png" alt="" width="35">
        <h4>Sign In</h4>
    </div>
    <div id="menu">
        <img src="./images/menu.png" width="35" alt="">
        <h4>MENU</h4>
    </div>
    <div id="hideMenu">
        <ul>
            <li id="openMyProfile">
                My Profile
                <form action="/profile" method="post">

                </form>
            </li>
            <li id="openMessages">
                <a href="/chat">My Messages</a>
            </li>
            <li id="openCreateThought">
                <a href="/create-thought" style="padding: 20px 0;">Create thought</a>
            </li>
            <li>
                <a href="/settings">Settings Profile</a>
            </li>
            <li id="logout">
                Logout
                <form action="/" method="post">
                    <input type="hidden" name="logout">
                    <input type="submit" value="Выйти">
                </form>
            </li>
        </ul>
    </div>
    <div id="logo">
        <a href="/"><img src="./images/logo.png" alt=""></a>
    </div>
    <div id="search">
        <img src="images/loop1.png" width="35">
        <div id="searchBlock">
            <button id="showFilters">Filters</button>
            <input id="liveSearchInput" type="text" placeholder="Search by name/lastname">
        </div>
    </div>
</nav>
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
                                                                                       href="/signUp">register</a></h3>
    </div>
</div>


<div id="greeting">
    <div id="slogan">
        <h1>Find you love</h1>
        <h1>Find yourself</h1>
    </div>
    <ul id="sprites">
        <li id="chocoSprite"><img src="../images/greeting/choco.png" width="100" alt=""></li>
        <li id="loveMessageSprite"><img src="../images/greeting/greenlovemessage.png" width="80" alt=""></li>
        <li id="handSprite"><img src="../images/greeting/whitehand.png" width="100" alt=""></li>
        <li id="homeSprite"><img src="../images/greeting/home.png" width="100" alt=""></li>
        <li id="loveBookSprite"><img src="../images/greeting/greenlovebook.png" width="100" alt=""></li>
        <li id="loveIsSprite"><img src="../images/greeting/loveis.png" width="160" alt=""></li>
    </ul>
</div>


<div id="content">
    <h1>The time has come. Take action!</h1>
    <button id="click">Click me</button>
    <ul id="userList">
        <li data-uid="<%= users.get(i).getId() %>">
            <div class="overlay">
                <div class="overBack"></div>
                <h2>
                </h2>
                <div class="userInfo">
                    <span>Город:</span><span></span>
                    <span>Возраст:</span><span></span>
                    <span>О себе:</span><span></span>
                </div>
                <div class="countLikes">
                    <img src="../images/like.png" width="15"/>
                    <span>20</span>
                </div>
            </div>
            <img src="/user-photo?id=<%= users.get(i).getId() %>" alt="">
            <form action="/profile" method="post" style="display: none">
                <input type="hidden" name="user-id" value="<%= users.get(i).getId() %>">
            </form>
        </li>
    </ul>
</div>

<#--<jsp:include page="footer.jsp"/>-->


<script>
    $(document).ready(function () {
        $("#click").click(function () {
            $.ajax({
                url: "/users",
                method: "GET",
                success: function (data, i) {
                    var a = data;
                    console.log();
                    data.forEach(function (item, i) {
                        $("#userList").append(
                            `<li data-uid="`+ item.id +`">
                                <div class="overlay">
                                    <div class="overBack"></div>
                                    <h2>` + item.firstName + ` `+ item.lastName +`</h2>
                                    <div class="userInfo">
                                        <span>Город:</span><span>`+ item.city +`</span>
                                        <span>Возраст:</span><span>`+ item.age +`</span>
                                        <span>О себе:</span><span>`+ item.shortAbout +`</span>
                                    </div>
                                    <div class="countLikes">
                                        <img src="../images/like.png" width="15"/>
                                        <span>`+ item.countLikes +`</span>
                                    </div>
                                </div>
                                <img src="/user-photo?id=`+ item.id +`" alt="">
                                <form action="/profile" method="post" style="display: none">
                                    <input type="hidden" name="user-id" value="`+ item.id +`">
                                </form>
                            </li>`
                        );
                    });
                }
            });
        });
    });

    $(document).ready(function () {
        // <% if(isAuth) { %>
        <#--        <#if req>-->
        $(".countLikes").on('click', function () {
            let currentLike = this
            let countLikes = Number($($(currentLike).children('span')).text())
            $.ajax({
                method: "POST",
                data: {
                    liked: "",
                    // from_user_id: <%= sessionUser.getId() %>,
                    to_user_id: currentLike.dataset.uid,
                    count_likes: countLikes
                }
            });
            let cur = $(currentLike), curImg = $($(cur).children('img')), curSpan = $($(cur).children('span'));
            if (curImg.attr("src") === "../images/like.png") {
                curImg.attr("src", "../images/activelike.png")
                curSpan.text(countLikes + 1)
            } else {
                curImg.attr("src", "../images/like.png")
                curSpan.text(countLikes - 1)
            }
        });
        <#--        </#if>-->
        // <% } %>

        // let users = <%= jsonUsers %>;

        $("#liveSearchInput").on('input', function () {
            let currentStr = this.value.trim().toLowerCase();

            users.forEach(function (user, i) {
                let nameLastname = (user.firstName + " " + user.lastName).toLowerCase();
                let lastnameName = (user.lastName + " " + user.firstName).toLowerCase();

                if (
                    nameLastname.search(currentStr) === -1 &&
                    lastnameName.search(currentStr) === -1
                )
                    $("#userList li[data-uid=" + user.id + "]").fadeOut(300);
                else
                    $("#userList li[data-uid=" + user.id + "]").fadeIn(300);

            });
        });

    });
</script>

</body>
</html>

<#--<!doctype html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <meta name="viewport"-->
<#--          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">-->
<#--    <meta http-equiv="X-UA-Compatible" content="ie=edge">-->
<#--    <title>Document</title>-->
<#--</head>-->
<#--<body>-->
<#--    <form action="/" method="post">-->
<#--        <input type="text" name="name">-->
<#--        <input type="email" name="email">-->
<#--        <input type="password" name="password">-->
<#--        <input type="submit" value="Exit">-->
<#--    </form>-->
<#--</body>-->
<#--</html>-->