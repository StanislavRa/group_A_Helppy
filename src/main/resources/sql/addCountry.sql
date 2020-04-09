USE helppy;

DROP TABLE IF EXISTS COUNTRY;

CREATE TABLE COUNTRY
            (ID            bigint NOT NULL AUTO_INCREMENT,
             CREATED_ON   datetime(6) DEFAULT NULL,
             UPDATED_ON   datetime(6) DEFAULT NULL,
             COUNTRY         varchar(60) NOT NULL,
PRIMARY KEY (ID),
UNIQUE KEY UK_1oegt2jxxn3mhf3hx59qcnra (COUNTRY))
ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO COUNTRY
(ID,    COUNTRY)
VALUES
(1,     'Estonia'),
(2,     'Sweden'),
(3,     'Finland');
