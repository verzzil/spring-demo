<nav>
    <#if isAuth == false>
        <div id="signIn">
            <img src=/images/signin.png" alt="" width="35">
            <h4>Sign In</h4>
        </div>
    <#else>
        <div id="menu">
            <img src=/images/menu.png" width="35" alt="">
            <h4>MENU</h4>
        </div>
        <div id="hideMenu">
            <ul>
                <li id="openMyProfile">
                    <a href="/profile/id${sessionUser.id}">My Profile</a>
                </li>
                <li id="openMessages">
                    <a href="/chat">My Messages</a>
                </li>
                <li id="openCreateThought">
                    <a href="/create-thought" style="padding: 20px 0;">Create thought</a>
                </li>
                <li>
                    <a href="/profile/id${sessionUser.id}/settings">Settings Profile</a>
                </li>
                <li id="logout">
                    <a href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </#if>
    <div id="logo">
        <a href="/"><img src="/images/logo.png" alt=""></a>
    </div>
    <#if isAuth == true>
        <div id="search">
            <img src="/images/loop1.png" width="35">
            <div id="searchBlock">
                <button id="showFilters">Filters</button>
                <input id="liveSearchInput" type="text" placeholder="Search by name/lastname">
            </div>
        </div>
    </#if>

</nav>
<div id="layers">
    <div id="layer1"></div>
    <div id="layer2"></div>
    <div id="layer3"></div>
    <div id="layer4"></div>
    <div id="layer5">
        <img src="/images/close.png" id="closeSignInForm" alt="">
        <form id="signInForm" action="/" method="post">
            <h1>Sign in</h1>
            <input type="text" name="email" autocomplete="none" placeholder="Enter email">
            <input type="password" name="password" placeholder="Enter password">
            <input type="hidden" name="sign-in">
            <label>
                <input type="checkbox" name="remember-me">Запомни меня
            </label>
            <input type="submit" value="Log in">
        </form>
        <#include "./layerSprite.ftlh">
        <h3 style="z-index: 20; color: #fff;">If you don't have an account, you can <a style="color: #fff"
                                                                                       href="/signUp">register</a></h3>
    </div>
</div>