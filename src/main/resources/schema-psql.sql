DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id UUID NOT NULL,
   name VARCHAR NOT NULL,
   email VARCHAR NOT NULL,
   age INTEGER NOT NULL,
   weight INTEGER NOT NULL,
   height INTEGER NOT NULL,
   goal VARCHAR NOT NULL,
   sex VARCHAR NOT NULL,
   daily_rate INTEGER NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);

DROP TABLE IF EXISTS dishes;

CREATE TABLE dishes (
  id UUID NOT NULL,
   name VARCHAR NOT NULL,
   calorie_amount INTEGER NOT NULL,
   protein INTEGER NOT NULL,
   fat INTEGER NOT NULL,
   carbohydrate INTEGER NOT NULL,
   CONSTRAINT pk_dishes PRIMARY KEY (id)
);

INSERT INTO users (id, name, email, age, weight, height, goal, daily_rate, sex) VALUES (uuid_generate_v4(), 'test1', 'test1@mail.ru', 10, 35, 140, 'MAINTENANCE', 100, 'MALE');
INSERT INTO users (id, name, email, age, weight, height, goal, daily_rate, sex) VALUES (uuid_generate_v4(), 'test2', 'test2@mail.ru', 30, 100, 180, 'GAIN', 100, 'MALE');
INSERT INTO users (id, name, email, age, weight, height, goal, daily_rate, sex) VALUES (uuid_generate_v4(), 'test3', 'test3@mail.ru', 28, 80, 190, 'SLIMMING', 100, 'FEMALE');

INSERT INTO dishes (id, name, calorie_amount, protein, fat, carbohydrate) VALUES (uuid_generate_v4(), 'testdish1', 1000, 150, 300, 560);
INSERT INTO dishes (id, name, calorie_amount, protein, fat, carbohydrate) VALUES (uuid_generate_v4(), 'testdish2', 500, 75, 150, 280);
INSERT INTO dishes (id, name, calorie_amount, protein, fat, carbohydrate) VALUES (uuid_generate_v4(), 'testdish3', 250, 30, 75, 140);