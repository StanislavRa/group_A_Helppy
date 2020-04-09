USE helppy;

DROP TABLE IF EXISTS CITY;

CREATE TABLE CITY
            (ID            bigint NOT NULL AUTO_INCREMENT,
             CREATED_ON   datetime(6) DEFAULT NULL,
             UPDATED_ON   datetime(6) DEFAULT NULL,
             CITY         varchar(60) NOT NULL,
             FK_COUNTRY   bigint DEFAULT NULL,

PRIMARY KEY (ID),
UNIQUE KEY UK_7di2inbwi1b53hs4y71pixils (CITY),
KEY FKbc3ccbf5ervnqkoc07l8838h5 (FK_COUNTRY),
CONSTRAINT FKbc3ccbf5ervnqkoc07l8838h5 FOREIGN KEY (FK_COUNTRY) REFERENCES country (ID)
ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO CITY
(ID,    CITY,         FK_COUNTRY)
VALUES
(1,     'Tallinn',    1),
(2,     'Tartu',      1),
(3,     'Narva',      1),
(4,     'Helsinki',   2),
(5,     'Tampere',    2),
(6,     'Stockholm',  3),
(7,     'Upsala',     3);
