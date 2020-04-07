USE helppy;

DROP TABLE IF EXISTS CITY;

CREATE TABLE CITY
            (ID            bigint NOT NULL AUTO_INCREMENT,
             CREATED_ON   datetime(6) DEFAULT NULL,
             UPDATED_ON   datetime(6) DEFAULT NULL,
             CITY         varchar(60) NOT NULL,
PRIMARY KEY (ID),
UNIQUE KEY UK_7di2inbwi1b53hs4y71pixils (CITY))
ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO CATEGORY
(ID,    CITY)
VALUES
(1,     'Tallinn'),
(2,     'Tartu'),
(3,     'Narva'),
(4,     'Helsinki'),
(5,     'Tampere'),
(6,     'Stockholm'),
(7,     'Upsala');
