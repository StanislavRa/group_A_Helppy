USE helppy;

INSERT INTO ADVERTISEMENT
            (ID, SUBJECT,       DESCRIPTION,          PRICE, TYPE,      STATE,      START_DATE,             END_DATE  ,             FK_CUSTOMER_ID)
VALUES      (1,'Clean Fast 24', 'Cleaning Cars',      12,   'OFFER',    'ACTIVE',  '2020-01-01 10:10:10',   '2020-01-01 10:10:10' , 2 ),
            (2,'RentToday!',    'Renting Apartments', 25,   'REQUEST',  'INACTIVE', '2020-01-01 10:10:10',  '2020-01-01 10:10:10',  2 );
