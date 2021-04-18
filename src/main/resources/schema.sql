create table if not exists account_likes(
    id_from_account int,
    id_to_account int,
    foreign key (id_from_account) references account(id) ON DELETE CASCADE,
    foreign key (id_to_account) references account(id) ON DELETE CASCADE
);

create table if not exists article_likes(
    id_from_account int,
    id_article int,
    foreign key (id_from_account) references account(id) ON DELETE CASCADE,
    foreign key (id_article) references article(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS persistent_logins (
    username  VARCHAR(64) NOT NULL,
    series    VARCHAR(64) NOT NULL,
    token     VARCHAR(64) NOT NULL,
    last_used TIMESTAMP   NOT NULL,
    PRIMARY KEY (series)
);
