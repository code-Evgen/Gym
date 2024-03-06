delete FROM trainee_trainer;
delete FROM trainee;
delete FROM trainer;
delete FROM user_table;
delete FROM training_type;
delete FROM training;
ALTER SEQUENCE user_table_id_seq restart;
ALTER SEQUENCE trainee_id_seq restart;
ALTER SEQUENCE trainer_id_seq restart;
ALTER SEQUENCE training_id_seq restart;
ALTER SEQUENCE training_type_id_seq restart;

insert into user_table(first_name, last_name, username, password) VALUES ('name', 'lastname1', 'username1', 'password123');
insert into trainee (user_id)
select id from user_table where username = 'username1';

insert into user_table(first_name, last_name, username, password) VALUES ('name', 'lastname2', 'username2', 'password123');
insert into trainee (user_id)
select id from user_table where username = 'username2';

insert into user_table(first_name, last_name, username, password) VALUES ('name', 'lastname3', 'username3', 'password123');
insert into trainer (user_id)
select id from user_table where username = 'username3';

insert into user_table(first_name, last_name, username, password) VALUES ('name', 'lastname4', 'username4', 'password123');
insert into trainer (user_id)
select id from user_table where username = 'username4';

insert into training_type (training_type_name) values ('RUNNING');
insert into training_type (training_type_name) values ('BOXING');

insert into training (trainee_id, trainer_id, training_name, training_type_id, training_date, training_duration)
VALUES (1, 1, 'training-1', 1, '20-01-2024', 60);
insert into trainee_trainer (trainee_id, trainer_id) values (1, 1);

insert into training (trainee_id, trainer_id, training_name, training_type_id, training_date, training_duration)
VALUES (1, 2, 'training-2', 2, '20-02-2024', 45);
insert into trainee_trainer (trainee_id, trainer_id) values (1, 2);

insert into training (trainee_id, trainer_id, training_name, training_type_id, training_date, training_duration)
VALUES (2, 1, 'training-3', 1, '20-01-2025', 60);
insert into trainee_trainer (trainee_id, trainer_id) values (2, 1);

insert into training (trainee_id, trainer_id, training_name, training_type_id, training_date, training_duration)
VALUES (2, 2, 'training-4', 2, '20-02-2025', 45);
insert into trainee_trainer (trainee_id, trainer_id) values (2, 2);