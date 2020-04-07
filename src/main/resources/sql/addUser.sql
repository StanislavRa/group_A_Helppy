USE helppy;

DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
                   ID bigint NOT NULL AUTO_INCREMENT,
                   LOGIN varchar(60) NOT NULL,
                   PASSWORD varchar(60) NOT NULL,
PRIMARY KEY (ID),
UNIQUE KEY UK_m6gjtxi6t4thhq8x960qif5cy (LOGIN))
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO USERS
(ID,            LOGIN,          PASSWORD)
VALUES
(1,             'peterG',      'password'),
(2,             'loisG',       'password');
