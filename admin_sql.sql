create table COMPANY
(
    ID INT not null
        primary key
);

create table banner_ad
(
    id         INTEGER not null
        primary key autoincrement,
    sort       INTEGER,
    url        TEXT,
    pic_url    TEXT,
    pic_width  INTEGER,
    pic_height INTEGER,
    status     TEXT,
    tel        TEXT
);

create table basis
(
    site_name    TEXT,
    key_words    TEXT,
    describe     TEXT,
    announcement TEXT,
    logo         TEXT,
    email        TEXT
);

create table couplets_ad
(
    id       INTEGER
        constraint couplets_ad_pk
            primary key autoincrement,
    location TEXT,
    url      TEXT,
    pic_url  TEXT,
    status   TEXT,
    tel      TEXT
);

create table float_ad
(
    id       INTEGER
        constraint float_ad_pk
            primary key autoincrement,
    location TEXT,
    url      INTEGER,
    pic_url  TEXT,
    status   TEXT,
    tel      status
);

create table statistics
(
    jsurl TEXT
);

create table top_ad
(
    id         INTEGER not null
        constraint top_ad_pk
            primary key autoincrement,
    sort       INTEGER,
    url        TEXT,
    pic_url    TEXT,
    pic_width  INTEGER,
    pic_height INTEGER,
    status     TEXT,
    tel        TEXT
);

create table user
(
    id   INTEGER not null
        primary key autoincrement,
    name varchar(20)
);

