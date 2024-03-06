insert into user_table(first_name, last_name, username, password) VALUES ('name', 'lastname', 'username', 'password123');
insert into trainee (user_id)
    select id from user_table where username = 'username';