create table roles
(
    id   uuid
        constraint role_pk PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    UNIQUE (name)
);

create table users
(
    id       uuid
        constraint user_pk PRIMARY KEY,
    email VARCHAR(60)  NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    UNIQUE (email)
);

create table user_role_relation
(
    role_id uuid REFERENCES roles (id) ON DELETE CASCADE,
    user_id uuid REFERENCES users (id) ON DELETE CASCADE,
    UNIQUE (role_id, user_id)
);

create table notes
(
    id          uuid
        constraint note_pk PRIMARY KEY,
    name        VARCHAR(96)   NOT NULL,
    content     VARCHAR(9600) NOT NULL,
    access_type VARCHAR(20)   NOT NULL,
    user_id     uuid          NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users ON DELETE CASCADE,
    constraint note_name_uniq UNIQUE (name, user_id)
);

