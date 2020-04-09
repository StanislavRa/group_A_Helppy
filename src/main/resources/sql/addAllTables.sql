USE helppy;

INSERT INTO COUNTRY
(ID,    COUNTRY)
VALUES
(1,     'Estonia'),
(2,     'Sweden'),
(3,     'Finland');

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

INSERT INTO CATEGORY
(ID,    NAME,                   FK_SUPER_CATEGORY)
VALUES
(1,     'Renting',              null),
(2,     'Cleaning',             null),
(3,     'Car Rent',             1),
(4,     'Bike Rent',            1),
(5,     'Apartment Cleaning',   2),
(6,     'Office Cleaning',      2);

INSERT INTO USER
(ID,            LOGIN,          PASSWORD)
VALUES
(1,             'peterG',      'password'),
(2,             'loisG',       'password');

INSERT INTO CUSTOMER
(FULL_NAME,    ID)
VALUES
('Peter Griffin',      1),
('Lois Griffin',       2);


INSERT INTO ADVERTISEMENT
            (ID, SUBJECT,       DESCRIPTION,          PRICE, TYPE,      STATE,      START_DATE,             END_DATE  ,             FK_ADDRESS_ID,   FK_CATEGORY_ID,   FK_CUSTOMER_ID)
VALUES      (1,'Clean Fast 24', 'Cleaning Cars',      12,   'OFFER',    'ACTIVE',  '2020-01-01 10:10:10',   '2020-01-01 10:10:10',  1 ,              2,                2 ),
            (2,'Clean Fast   ', 'Cleaning Cars',      13,   'OFFER',    'ACTIVE',  '2020-01-01 10:10:10',   '2020-01-01 10:10:10',  4 ,              3,                1 ),
            (3,'Clean Fast   ', 'Cleaning Cars',      120,  'OFFER',    'ACTIVE',  '2020-01-01 10:10:10',   '2020-01-01 10:10:10',  5 ,              2,                1 ),
            (4,'Clean Fast   ', 'Cleaning ',          90,   'OFFER',    'ACTIVE',  '2020-01-01 10:10:10',   '2020-01-01 10:10:10',  3 ,              1,                2 ),
            (5,'RentToday!',    'Renting Apartments', 25,   'REQUEST',  'INACTIVE', '2020-01-01 10:10:10',  '2020-01-01 10:10:10',  2 ,              3,                2 );
