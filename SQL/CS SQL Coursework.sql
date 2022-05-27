<!-- Coursework Task 3 -->
CREATE TABLE Activity (
	aID INT NOT NULL,
	aType VARCHAR(255) NOT NULL,
	aPrice REAL NOT NULL,
	aDateAndTime DATETIME NOT NULL,
	gID INT NOT NULL,
	gName VARCHAR(255) NOT NULL,
	sID INT NOT NULL,
	CONSTRAINT aID_pk PRIMARY KEY (aID),
	CONSTRAINT sID_fk FOREIGN KEY (sID)
		REFERENCES Staff(sID)
);

CREATE TABLE Staff (
	sID INT NOT NULL,
	sName VARCHAR(255) NOT NULL,
	sEmail VARCHAR(255) NOT NULL,
	sEmployment VARCHAR(255) NOT NULL,
	CONSTRAINT sID_pk PRIMARY KEY (sID)
);

<!-- Coursework Task 4 -->
INSERT INTO Staff (sID, sName, sEmail, sEmployment) VALUES (1, 'Emma', 'emma@coolcitytours.com', 'Full-time');
INSERT INTO Staff (sID, sName, sEmail, sEmployment) VALUES (2, 'Nicholas', ' nick@coolcitytours.com', 'Part-time');
INSERT INTO Staff (sID, sName, sEmail, sEmployment) VALUES (3, 'Laura', 'laura@coolcitytours.com', 'Full-time');

SELECT sID FROM Staff WHERE sName = 'Emma';

INSERT INTO Activity (aID, aType, aPrice, aDateAndTime, gID, gName, sID) 
VALUES (10, 'River Boat (day)', 24.99, '2018-09-10 11:00:00', 300, 'Adrian', (SELECT sID FROM Staff WHERE sID = '2'));

INSERT INTO Activity (aID, aType, aPrice, aDateAndTime, gID, gName, sID) 
VALUES (20, 'Night walk', 6.49, '2018-10-31 21:30:00', 100, 'Lucy', (SELECT sID FROM Staff WHERE sID = '1'));

INSERT INTO Activity (aID, aType, aPrice, aDateAndTime, gID, gName, sID) 
VALUES (30, 'Segway tour', 21.99, '2018-07-11 16:15:00', 400, 'Elizabeth', (SELECT sID FROM Staff WHERE sID = '3'));

INSERT INTO Activity (aID, aType, aPrice, aDateAndTime, gID, gName, sID) 
VALUES (40, 'Walking tour', 4.99, '2019-04-02 10:45:00', 200, 'Fatima', (SELECT sID FROM Staff WHERE sID = '1'));

INSERT INTO Activity (aID, aType, aPrice, aDateAndTime, gID, gName, sID) 
VALUES (50, 'River Boat (night)', 29.99, '2019-02-14 19:00:00', 500, 'Steve', (SELECT sID FROM Staff WHERE sID = '2'));

<!-- Coursework Task 5 -->
SELECT aID, aType, aDateAndTime, aPrice 
	FROM Activity 
	WHERE aPrice < 24.00;

SELECT aType, aPrice, aDateAndTime
	FROM Activity
	WHERE aDateAndTime > '2018-08-01' AND aDateAndTime < '2019-02-28';

SELECT *
	FROM Staff
	WHERE sEmail LIKE '%a@coolcitytours.com'
	ORDER BY sName DESC;

SELECT sName, 
	AVG (aPrice) AS sPAvg
	FROM Staff
	INNER JOIN Activity ON Activity.sID = Staff.sID
	GROUP BY sName;