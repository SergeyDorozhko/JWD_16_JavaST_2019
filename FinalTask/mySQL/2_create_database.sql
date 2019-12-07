CREATE DATABASE `poputka_db` DEFAULT CHARACTER SET utf8;

CREATE USER poputkaApp IDENTIFIED BY 'app';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `poputka_db`.*
TO `poputkaApp`@`localhost`;




GRANT SELECT,INSERT,UPDATE,DELETE
ON `poputka_db`.*
TO poputkaApp@'%';