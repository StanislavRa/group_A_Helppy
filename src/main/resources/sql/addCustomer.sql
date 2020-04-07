USE helppy;

DROP TABLE IF EXISTS CUSTOMER;

CREATE TABLE CUSTOMER
             (FULL_NAME    varchar(60) NOT NULL,
              ID           bigint NOT NULL,
PRIMARY KEY (ID),
CONSTRAINT FK6vdwguhavldpetqye244i3qea FOREIGN KEY (ID) REFERENCES user (ID))
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO CUSTOMER
(FULL_NAME,    ID)
VALUES
('Peter Griffin',      1),
('Lois Griffin',       2);