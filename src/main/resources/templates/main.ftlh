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

<#include "./nav.ftlh">

<div id="greeting">
    <div id="slogan">
        <h1>Find you love</h1>
        <h1>Find yourself</h1>
    </div>
    <ul id="sprites">
        <li id="chocoSprite"><img src="/images/greeting/choco.png" width="100" alt=""></li>
        <li id="loveMessageSprite"><img src="/images/greeting/greenlovemessage.png" width="80" alt=""></li>
        <li id="handSprite"><img src="/images/greeting/whitehand.png" width="100" alt=""></li>
        <li id="homeSprite"><img src="/images/greeting/home.png" width="100" alt=""></li>
        <li id="loveBookSprite"><img src="/images/greeting/greenlovebook.png" width="100" alt=""></li>
        <li id="loveIsSprite"><img src="/images/greeting/loveis.png" width="160" alt=""></li>
    </ul>
</div>


<div id="content">
    <h1>The time has come. Take action!</h1>
    <ul id="userList">
        <#--        <#list users as user>-->
        <#--        <li data-uid="${user.id}">-->
        <#--            <div class="overlay">-->
        <#--                <div class="overBack"></div>-->
        <#--                <h2>${user.firstName} ${user.lastName}</h2>-->
        <#--                <div class="userInfo">-->
        <#--                    <span>Город:</span><span>${user.city}</span>-->
        <#--                    <span>Возраст:</span><span>${user.age}</span>-->
        <#--                    <span>О себе:</span><span>${user.shortAbout}</span>-->
        <#--                </div>-->
        <#--                <div class="countLikes">-->
        <#--                    <img src="/images/like.png" width="15"/>-->
        <#--                    <span>${user.countLikes}</span>-->
        <#--                </div>-->
        <#--            </div>-->
        <#--            <img src="/user-photo?id=<%= users.get(i).getId() %>" alt="">-->
        <#--            <form action="/profile" method="get" style="display: none">-->
        <#--                <input type="hidden" name="user-id" value="${user.id}">-->
        <#--            </form>-->
        <#--        </li>-->
        <#--        </#list>-->
    </ul>
    <button id="load_new_users">Прогрузить ещё</button>
</div>

<#include "./footer.ftlh">

<script>
    $(document).ready(function () {
        let likedUsersIdsJs = new Set();
        <#if likedUsersIds??>
        <#list likedUsersIds as int>
        likedUsersIdsJs.add(${int})
        </#list>
        </#if>
        let addUser = function (item) {
            let likeView = likedUsersIdsJs.has(item.id) ? "/images/activelike.png" : "/images/like.png";
            $("#userList").append(
                `<li>
                                <div class="overlay">
                                    <div class="overBack"></div>
                                    <h2>` + item.firstName + ` ` + item.lastName + `</h2>
                                    <div class="userInfo">
                                        <span>Город:</span><span>` + item.city + `</span>
                                        <span>Возраст:</span><span>` + item.age + `</span>
                                        <span>О себе:</span><span>` + item.shortAbout + `</span>
                                    </div>
                                    <div class="countLikes" data-uid="` + item.id + `">
                                        <img src="` + likeView + `" width="15"/>
                                        <span>` + item.countLikes + `</span>
                                    </div>
                                </div>
                                <img src="/files/` + item.id + `" alt="">
                                <form method="GET" action="/profile/id` + item.id + `"
                                    style="display:none;"></form>
                            </li>`
            );
        };

        let currentUserPage = 0;
        $.ajax({
            url: "/users/ajax",
            method: "GET",
            data: {page: currentUserPage++},
            success: function (data) {
                data.forEach(function (item, i) {
                    addUser(item);
                });
            }
        });

        $("#load_new_users").click(function () {
            $.ajax({
                url: "/users/ajax",
                method: "GET",
                data: {page: currentUserPage++},
                success: function (data) {
                    data.forEach(function (item, i) {
                        addUser(item);
                    });
                }
            });
        });

        <#if isAuth == true>
        $(document).on('click', ".countLikes", function () {
            let currentLike = this
            console.log(currentLike);
            let countLikes = Number($($(currentLike).children('span')).text())
            $.ajax({
                url: "/like/user",
                method: "POST",
                data: {
                    id_from_user: ${sessionUser.id},
                    id_to_user: currentLike.dataset.uid,
                }
            });
            let cur = $(currentLike), curImg = $($(cur).children('img')), curSpan = $($(cur).children('span'));
            if (curImg.attr("src") === "/images/like.png") {
                curImg.attr("src", "/images/activelike.png")
                curSpan.text(countLikes + 1)
            } else {
                curImg.attr("src", "/images/like.png")
                curSpan.text(countLikes - 1)
            }
        });
        </#if>
        $(document).on('click', '#userList li', function () {
            $(this).children('form').submit();
        });
    });

    $(document).ready(function () {

        // let users = <%= jsonUsers %>;

        $("#liveSearchInput").on('input', function () {
            let currentStr = this.value.trim().toLowerCase();

            // #TODO: Сделать ajax поиск юзеров
        });

    });
</script>

</body>
</html>