DROP TABLE IF EXISTS door;


if not exists (
CREATE TABLE door (
 numDoor CHAR(1) NOT NULL,
 access BOOLEAN,
 accessLevel CHAR(1),
 accessType VARCHAR(20),
 time DATETIME,
 PRIMARY KEY (numDoor));
)

