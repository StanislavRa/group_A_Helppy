USE helppy;


INSERT INTO ADVERTISEMENT
            (ID, SUBJECT,       DESCRIPTION,          PRICE, TYPE,      STATE,      START_DATE,             END_DATE  ,             FK_ADDRESS_ID,   FK_CATEGORY_ID,   FK_CUSTOMER_ID)
VALUES      (1,'Clean Fast 24', 'Cleaning Cars',      12,   'OFFER',    'ACTIVE',  '2020-01-01 10:10:10',   '2020-01-01 10:10:10',  1 ,              2,                2 ),
            (2,'Clean Fast   ', 'Cleaning Cars',      13,   'OFFER',    'ACTIVE',  '2020-01-01 10:10:10',   '2020-01-01 10:10:10',  4 ,              3,                1 ),
            (3,'Clean Fast   ', 'Cleaning Cars',      120,  'OFFER',    'ACTIVE',  '2020-01-01 10:10:10',   '2020-01-01 10:10:10',  5 ,              2,                1 ),
            (4,'Clean Fast   ', 'Cleaning ',          90,   'OFFER',    'ACTIVE',  '2020-01-01 10:10:10',   '2020-01-01 10:10:10',  3 ,              1,                2 ),
            (5,'RentToday!',    'Renting Apartments', 25,   'REQUEST',  'INACTIVE', '2020-01-01 10:10:10',  '2020-01-01 10:10:10',  2 ,              3,                2 );
