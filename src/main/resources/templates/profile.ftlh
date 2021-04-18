<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${currentProfile.getFullName()}</title>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/profile.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Bungee+Shade&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="../js/main.js"></script>
    <script src="../js/submitForms.js"></script>
    <script src="../js/profile.js"></script>

    <style>
        #layerSprite {
            animation: layerAnim 20s ease-in-out infinite;
        }
    </style>

    <#if isMyPage == true>
        <style>
            #likeUser, .thoughtsLike {
                cursor: default !important;
            }
        </style>
    </#if>
</head>
<body>

<#include "./nav.ftlh">

<div id="profile">
    <div id="user">
        <img src="/files/${currentProfile.id}" alt="">
        <div id="userInfo">
            <h1>${currentProfile.getFullName()}</h1>
            <div id="userInfo2">
                <span>Возраст:</span><span>${currentProfile.age}</span>
                <span>Город:</span><span>${currentProfile.city}</span>
                <#if currentProfile.fullAbout??>
                    <span>О себе:</span><span>${currentProfile.fullAbout}</span>
                </#if>
            </div>
        </div>
        <#if isAuth == true && isMyPage != true>
            <form action="/chat" method="post">
                <input type="hidden" name="newDialog" value="${currentProfile.id}">
                <input type="hidden" name="name" value="${currentProfile.getFullName()}">
                <input type="submit" value="Send Message">
            </form>
        </#if>
        <div id="likeUser" data-uid="${currentProfile.id}">
            <#if isLikedProfile?? && isLikedProfile == true>
                <img src="/images/thoughtActiveLike.png" width="30">
            <#else>
                <img src="/images/thoughtLike.png" width="30">
            </#if>
            <#--            <img src="<%= usersIdWhereLiked.contains(user.getId()) ? "../images/thoughtActiveLike.png" : "../images/thoughtLike.png" %>" width="30" alt="">-->
            <span>${currentProfile.countLikes}</span>
        </div>
    </div>
    <div id="selection">
        <span id="thoughtsSelected" style="background: #77ffcd;">Thoughts</span>
        <span id="likedUsersSelected">Liked users</span>
    </div>
    <div id="frame">
        <div id="thoughts">
            <ul>
                <#list articles as article>
                    <li>
                        <span>${article.text}</span>
                        <div class="actionsBlock">
                            <div class="thoughtsLike" data-uid="${article.id}" data-aid="${article.id}">
                                <#assign liked = false>
                                <#list likedArticles as likedArticle>
                                    <#if likedArticle.id == article.id>
                                        <#assign liked = true>
                                        <#break>
                                    </#if>
                                </#list>
                                <#if liked == true>
                                    <img src="/images/thoughtActiveLike.png" width="20" alt="">
                                <#else>
                                    <img src="/images/thoughtLike.png" width="20" alt="">
                                </#if>
                                <span class="countLikesThought">${article.countLikes} likes</span>
                            </div>
                            <#if isMyPage == true>
                                <div class="deleteThought" data-id="${article.id}">
                                    <img src="../images/deleteThought.png" width="20" alt="">
                                </div>
                            </#if>
                        </div>
                    </li>
                <#else>
                    <li style='text-align: center'>У этого пользователя нет мыслей, а может, и извилин...</li>
                </#list>
            </ul>
        </div>
        <div id="likedUsers">
            <ul>
                <#list likedUsers as user>
                    <li>
                    <span>${currentProfile.getFullName()} проявил симпатию к
                        <span class="openLikedUser" style="cursor:pointer;color: #5953FF; font-weight: bold">
                            <a href="/profile/id${user.id}">${user.getFullName()}</a>
                        </span>
                    </span>
                    </li>
                <#else>
                    <li>Этому пользователю никто не нравится, скорее всего он любит только себя...</li>
                </#list>
            </ul>
        </div>
    </div>
</div>


<script>
    $(document).ready(function () {
        $(".deleteThought").click(function () {
            let currentEl = this;
            $.ajax({
                url: "/article/delete",
                method: "POST",
                data: {
                    article_id: this.dataset.id
                },
                success: function () {
                    $(currentEl).parent().parent().slideToggle(function () {
                        this.remove()
                    });
                }
            });
        });

        <#if isAuth == true>
        $(".thoughtsLike").click(function () {
            let currentLike = this
            let countLikes = Number($(currentLike).children("span").text().split(" ")[0])
            $.ajax({
                url: "/like/article",
                method: "POST",
                data: {
                    id_from_user: ${sessionUser.id},
                    id_to_article: currentLike.dataset.uid,
                }
            });
            if ($(currentLike).children("img").attr("src") === "/images/thoughtLike.png") {
                $(currentLike).children("img").attr("src", "/images/thoughtActiveLike.png");
                $(currentLike).children("span").text(countLikes + 1 + " likes");
            } else {
                $(currentLike).children("img").attr("src", "/images/thoughtLike.png");
                $(currentLike).children("span").text(countLikes - 1 + " likes");
            }
        });
        $("#likeUser").click(function () {
            let currentLike = this
            let countLikes = Number($("#likeUser span").text())
            $.ajax({
                url: "/like/user",
                method: "POST",
                data: {
                    id_from_user: ${sessionUser.id},
                    id_to_user: currentLike.dataset.uid,
                }
            });
            if ($("#likeUser img").attr("src") === "/images/thoughtLike.png") {
                $("#likeUser img").attr("src", "/images/thoughtActiveLike.png")
                $("#likeUser span").text(countLikes + 1)
            } else {
                $("#likeUser img").attr("src", "/images/thoughtLike.png")
                $("#likeUser span").text(countLikes - 1)
            }
        });
        </#if>
    });
</script>
</body>
</html>