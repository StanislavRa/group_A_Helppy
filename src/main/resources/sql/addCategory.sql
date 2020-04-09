USE helppy;

DROP TABLE IF EXISTS CATEGORY;

CREATE TABLE    CATEGORY
                (ID                 bigint NOT NULL AUTO_INCREMENT,
                CREATED_ON          datetime DEFAULT NULL,
                UPDATED_ON          datetime DEFAULT NULL,
                NAME varchar(255)   NOT NULL,
                FK_SUPER_CATEGORY   bigint DEFAULT NULL,
PRIMARY KEY (ID),
KEY FKi4xpgqxbyr04g7uhyay6autme (FK_SUPER_CATEGORY))
ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO CATEGORY
(ID,    NAME,                   FK_SUPER_CATEGORY)
VALUES
(1,     'Renting',              null),
(2,     'Cleaning',             null),
(3,     'Car Rent',             1),
(4,     'Bike Rent',            1),
(5,     'Apartment Cleaning',   2),
(6,     'Office Cleaning',      2);

