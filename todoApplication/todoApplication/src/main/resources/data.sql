insert into users(user_id, email, password, name, level)
values('1','123@email.com', '1234', 'kim12', 1);

insert into users(user_id, email, password, name, level)
values('2','456@email.com', '5678', 'hong12', 1);

insert into users(user_id, email, password, name, level)
values('3','789@email.com', '0000', 'park34', 3);

insert into users(user_id, email, password, name, level)
values('4','email@email.com', '0000', 'lee', 2);

-- Follow 테이블에 더미 팔로우 데이터 삽입
insert into follow(follower, following)
values ('1', '2'),('1', '3'),('1', '4'),('2','1');

-- Todo 더미데이터
insert into todo(todo_id, user_id, todo_date, task)
values (1, '1', current_date(), 'todo1');

insert into todo(todo_id, user_id, todo_date, task)
values (2, '2', current_date(), 'todo2');

-- Like 더미데이터
insert into likes(user_id, todo_id)
values('1', 1);

insert into likes(user_id, todo_id)
values('2', 2);