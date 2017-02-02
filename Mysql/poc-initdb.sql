ALTER USER 'root'@'localhost' IDENTIFIED BY 'admin';
CREATE DATABASE poc;
CREATE USER 'poc'@'localhost' IDENTIFIED BY 'poc';
GRANT ALL PRIVILEGES ON poc.* TO 'poc'@'localhost' WITH GRANT OPTION;