create table bebidas(
    id bigint unique not null auto_increment,
    image varchar(500) unique not null,
    title varchar(250) unique not null,
    creation datetime not null,
    characteristics varchar(500) not null,
    energetic varchar(50) not null,
    totalfat varchar(50) not null,
    saturatedfat varchar(50) not null,
    carbohydrate varchar(50) not null,
    protein varchar(50) not null,
    sodium varchar(50) not null,
    amount varchar(50) not null,
    primary key (id)
);