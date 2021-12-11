create table users
(
    username varchar(255) not null,
    password text not null,
    enabled int not null,
    constraint users_pk
        primary key (username)
);



create table authorities
(
    authority varchar(255) not null,
    username varchar(255) not null,
    id int auto_increment,
    constraint authorities_pk
        primary key (id)
);


