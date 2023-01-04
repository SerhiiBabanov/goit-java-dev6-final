create table friend
(
    user_id uuid,
    friend_id uuid,
    primary key(user_id,friend_id),
    foreign key(user_id) references users (id),
    foreign key(friend_id) references users (id)
)