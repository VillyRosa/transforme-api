create table users (
    id UUID primary key,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    birthdate timestamp not null,
    email varchar(80) unique not null,
    email_verified_at timestamptz,
    password varchar(255) not null,
    height_cm int,
    status varchar(50) not null default 'ACTIVE',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);