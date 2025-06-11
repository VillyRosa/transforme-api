create table meals (
    id UUID primary key,
    user_id UUID not null references users(id) on delete cascade,
    meal_time timestamp not null,
    type varchar(50) not null,
    description text,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);