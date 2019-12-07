CREATE DATABASE `library_db1` DEFAULT CHARACTER SET utf8;

CREATE USER library_user1 IDENTIFIED BY 'password';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `library_db1`.*
TO library_user1@localhost
IDENTIFIED BY 'password';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `library_db1`.*
TO library_user1@'%'
IDENTIFIED BY 'password';