

INSERT INTO LIGHT (ID, LEVEL, STATUS) VALUES (1, 20,'ON');
INSERT INTO NOISE (ID, LEVEL, STATUS) VALUES (1, 30,'ON');
INSERT INTO ROOM (ID, LIGHT_ID, NOISE_ID) VALUES (1, 1, 1);

INSERT INTO LIGHT (ID, LEVEL, STATUS) VALUES (2, 50,'OFF');
INSERT INTO NOISE (ID, LEVEL, STATUS) VALUES (2, 50,'OFF');
INSERT INTO ROOM (ID, LIGHT_ID, NOISE_ID) VALUES (2, 2, 2);