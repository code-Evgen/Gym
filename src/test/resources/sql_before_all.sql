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

insert into user_table(first_name, last_name, username, password) VALUES ('basename', 'baselastname', 'baseusername', 'Password123');

