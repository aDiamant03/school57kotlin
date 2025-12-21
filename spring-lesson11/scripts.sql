create table public.users
(
    id         bigserial
        constraint user_pk
            primary key,
    last_name  varchar(255) not null,
    first_name varchar(255)
);

create table public.account
(
    id      bigserial
        constraint account_pk
            primary key,
    user_id bigint           not null,
    amount  bigint default 0 not null,
    constraint check_name
        check (amount >= 0)
);

alter table public.users
    add age integer;

alter table public.users
    add constraint check_age
        check (age > 0);

insert into public.users (id, last_name, first_name)
values (default, 'Иванов', 'Иван');

insert into public.users (id, last_name, first_name)
values (default, 'Петров', 'Петр');

insert into public.users (id, last_name, first_name)
values (default, 'Петров2', 'Петр2');

update public.users
set first_name = 'Дмитрий'
where id = 3;

delete from public.users
where first_name = 'Дмитрий';

insert into public.account (id, user_id, amount)
values (default, 1, 100);

insert into public.account (id, user_id, amount)
values (default, 2, 200);

select u.last_name, u.first_name, u.age, a.amount
from users u
join account a on u.id = a.user_id;

begin transaction;
insert into account(id, user_id, amount)
values (default, 5, -100);
rollback;

begin transaction;
update account set amount = amount - 10 where id = 1;
update account set amount = amount + 10 where id = 2;
commit;

alter table account
    add constraint account_users_id_fk
        foreign key (user_id) references users;