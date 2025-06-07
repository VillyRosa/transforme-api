create table weights (
    id UUID primary key,
    user_id UUID not null references users(id) on delete cascade,
    weight_kg numeric(5,2) not null check (weight_kg > 0),
    measured_at timestamp not null default current_timestamp,
    note text,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);