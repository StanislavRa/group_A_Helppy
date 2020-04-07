USE helppy;

DROP TABLE IF EXISTS ADDRESS;

CREATE TABLE ADDRESS
            (ID           bigint NOT NULL AUTO_INCREMENT,
             CREATED_ON   datetime(6) DEFAULT NULL,
             UPDATED_ON   datetime(6) DEFAULT NULL,
             FK_CITY      bigint DEFAULT NULL,
             FK_COUNTRY   bigint DEFAULT NULL,
PRIMARY KEY (ID),
KEY FK5mm9k6mkqfxsi4vkq3d697jpb (FK_CITY),
KEY FK2cwqdktdurdousg9rl5o57214 (FK_COUNTRY),
CONSTRAINT FK2cwqdktdurdousg9rl5o57214 FOREIGN KEY (FK_COUNTRY) REFERENCES COUNTRY (ID),
CONSTRAINT FK5mm9k6mkqfxsi4vkq3d697jpb FOREIGN KEY (FK_CITY) REFERENCES CITY (ID))
ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO ADDRESS
(ID,     FK_CITY,    FK_COUNTRY)
VALUES
(1,     1,              1),
(2,     2,              1),
(3,     3,              1),
(4,     4,              2),
(5,     5,              2),
(6,     6,              3),
(7,     7,              3);
