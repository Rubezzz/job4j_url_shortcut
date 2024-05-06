create table url (
    id serial primary key not null,
    url varchar(256) unique,
    code varchar(7) unique,
    total int DEFAULT 0,
    user_id int references users(id)
);