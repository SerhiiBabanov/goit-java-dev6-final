create table friends_notes
(
    user_id uuid,
    friend_note_id uuid,
    primary key(user_id,friend_note_id),
    foreign key(user_id) references users (id),
    foreign key(friend_note_id) references notes (id)
)