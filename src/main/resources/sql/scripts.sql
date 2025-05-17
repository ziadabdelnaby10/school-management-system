create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

insert ignore into `users` values ('user','{noop}ziad12345' , '1');
insert ignore into `authorities` values ('user' , 'read');

insert ignore into `users` values ('admin','{bcrypt}$2a$12$poqBFUojTjgdrvyXDrltyObz4.Fis6nsLSCWK6ZpM5E6pE0fzHjra' , '1');
insert ignore into `authorities` values ('admin' , 'admin');