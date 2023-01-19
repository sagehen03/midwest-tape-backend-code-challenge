create table Address
(
    id        bigint              not null primary key,
    address1 varchar(255)         not null,
    address2  varchar(255)                ,
    city  varchar(255)            not null,
    state  varchar(100)           not null,
    postal  varchar(10)           not null
);

insert into Address(id, address1, address2, city, state, postal)
values (1, '226 Causeway St', 'Suite 300', 'Boston', 'MA', '02114'),
(2, '118 High St', 'Unit 7', 'Boston', 'MA', '02129');


create table User
(
    id        bigint              not null primary key,
    firstName varchar(255)        not null,
    lastName  varchar(255)        not null,
    username  varchar(255) unique not null,
    password  varchar(255)        not null, -- WHAT!? NOT ENCRYPTED!? ;-)
    address_id bigint             not null,
    foreign key (address_id) references Address(id)
);

insert into User
    (id, firstName, lastName, username, password, address_id)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123', 1) ,
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234', 2);

