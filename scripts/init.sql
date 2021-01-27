create table if not exists team
(
    id         bigint auto_increment
        primary key,
    commission double       null,
    created_at datetime(6)  null,
    currency   varchar(255) null,
    name       varchar(255) null,
    updated_at datetime(6)  null
);

create table if not exists player
(
    id         bigint auto_increment
        primary key,
    birth_date datetime(6)    null,
    created_at datetime(6)    null,
    experience int            null,
    name       varchar(255)   null,
    price      decimal(19, 2) null,
    updated_at datetime(6)    null,
    team_id    bigint         null,
    constraint FKdvd6ljes11r44igawmpm1mc5s
        foreign key (team_id) references team (id)
);



INSERT INTO test.team (id, commission, created_at, currency, name, updated_at) VALUES (1, 2.4, '2021-01-22 22:56:22.089000', 'EUR', 'Bayern Munich', '2021-01-21 22:56:22.089000');
INSERT INTO test.team (id, commission, created_at, currency, name, updated_at) VALUES (2, 5.3, '2021-01-23 09:01:31.210000', 'GBP', 'Manchester United', '2021-01-23 09:01:31.210000');
INSERT INTO test.team (id, commission, created_at, currency, name, updated_at) VALUES (3, 4.2, '2021-01-23 09:01:46.110000', 'GBP', 'Arsenal', '2021-01-23 09:01:46.110000');
INSERT INTO test.team (id, commission, created_at, currency, name, updated_at) VALUES (4, 5.3, '2021-01-24 22:56:00.690000', 'TRY', 'Besiktas', '2021-01-24 22:56:00.690000');

INSERT INTO test.player (id, birth_date, created_at, experience, name, price, updated_at, team_id) VALUES (1, '1995-01-23 08:40:26.418000', '2021-01-23 08:21:15.283000', 4, 'Lewandowski', 100000.00, '2021-01-22 08:41:15.283000', 1);
INSERT INTO test.player (id, birth_date, created_at, experience, name, price, updated_at, team_id) VALUES (2, '1985-01-23 08:40:26.418000', '2021-01-23 08:41:15.283000', 4, 'Muller', 22222220.00, '2021-01-23 08:41:15.283000', 1);
INSERT INTO test.player (id, birth_date, created_at, experience, name, price, updated_at, team_id) VALUES (3, '1999-01-23 08:40:26.418000', '2021-01-23 08:42:43.677000', 12, 'Mario Gomez', 2222220.00, '2021-01-23 08:42:43.677000', 1);
INSERT INTO test.player (id, birth_date, created_at, experience, name, price, updated_at, team_id) VALUES (4, '1990-01-23 09:00:11.556000', '2021-01-23 09:02:50.722000', 21, 'Zinedine Zidane', 222202.00, '2021-01-23 09:02:50.722000', 2);
INSERT INTO test.player (id, birth_date, created_at, experience, name, price, updated_at, team_id) VALUES (5, '2010-01-20 09:00:11.556000', '2021-01-23 09:03:56.239000', 7, 'Poul pogba', 322223202.00, '2021-01-23 09:03:56.239000', 3);
INSERT INTO test.player (id, birth_date, created_at, experience, name, price, updated_at, team_id) VALUES (6, '2001-01-24 22:49:28.333000', '2021-01-24 22:50:55.328000', 3, 'Quaresma', 1000000.00, '2021-01-24 22:50:55.328000', 4);
INSERT INTO test.player (id, birth_date, created_at, experience, name, price, updated_at, team_id) VALUES (7, '1990-01-24 22:53:25.740000', '2021-01-24 22:54:08.731000', 4, 'Talisca', 111101.00, '2021-01-24 22:54:08.732000', 4);

