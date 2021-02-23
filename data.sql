DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students (id bigserial PRIMARY KEY, name VARCHAR(255), mark INT);
INSERT INTO students (name, mark) VALUES
('Alexander', 20),
('Bob', 15),
('John', 10);