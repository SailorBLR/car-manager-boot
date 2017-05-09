INSERT INTO PRODUCERS ( PRODUCER_ID, PRODUCER_NAME)  VALUES ( 1,'BMW');
INSERT INTO PRODUCERS ( PRODUCER_ID, PRODUCER_NAME)  VALUES ( 2,'Audi');

INSERT INTO MODELS (MODEL_ID, PRODUCER_ID, MODEL_NAME) VALUES (1,1,'325');
INSERT INTO MODELS (MODEL_ID, PRODUCER_ID, MODEL_NAME) VALUES (2,1,'520');
INSERT INTO MODELS (MODEL_ID, PRODUCER_ID, MODEL_NAME) VALUES (3,2,'A4');
INSERT INTO MODELS (MODEL_ID, PRODUCER_ID, MODEL_NAME) VALUES (4,2,'A6');

INSERT INTO CARS (CAR_ID, PRODUCER_ID, MODEL_ID, PRODUCTION_YEAR, FUEL, TRANSMISSION)
VALUES (1,
        1,
        1,
        parsedatetime('01-01-2001 00:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),
        'diesel',
        'automatic');
INSERT INTO CARS (CAR_ID, PRODUCER_ID, MODEL_ID, PRODUCTION_YEAR, FUEL, TRANSMISSION)
VALUES (2,
        1,
        2,
        parsedatetime('01-01-2001 00:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),
        'gas',
        'automatic');
INSERT INTO CARS (CAR_ID, PRODUCER_ID, MODEL_ID, PRODUCTION_YEAR, FUEL, TRANSMISSION)
VALUES (3,
        1,
        1,
        parsedatetime('01-01-2001 00:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),
        'diesel',
        'mechanic');
INSERT INTO CARS (CAR_ID, PRODUCER_ID, MODEL_ID, PRODUCTION_YEAR, FUEL, TRANSMISSION)
VALUES (4,
        2,
        3,
        parsedatetime('01-01-2001 00:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),
        'diesel',
        'automatic');
INSERT INTO CARS (CAR_ID, PRODUCER_ID, MODEL_ID, PRODUCTION_YEAR, FUEL, TRANSMISSION)
VALUES (5,
        2,
        3,
        parsedatetime('01-01-2001 00:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),
        'gazoline',
        'mechanic');
INSERT INTO CARS (CAR_ID, PRODUCER_ID, MODEL_ID, PRODUCTION_YEAR, FUEL, TRANSMISSION)
VALUES (6,
        2,
        4,
        parsedatetime('01-01-2001 00:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),
        'diesel',
        'automatic');
