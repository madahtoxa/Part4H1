-- Возраст студента не может быть меньше 16 лет.
-- Имена студентов должны быть уникальными и не равны нулю.
-- Пара “значение названия” - “цвет факультета” должна быть уникальной.
-- При создании студента без возраста ему автоматически должно присваиваться 20 лет.

alter table student
    add constraint age_constraint check (age > 16)

alter table student
    add constaint name_unique unique (name);

alter table student
    alter column name set not null;

alter TABLE faculty
    add constraint name_color_unique unique (name, color);

alter TABLE student
    alter column age set default 20;