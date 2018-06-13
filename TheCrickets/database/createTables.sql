DROP TABLE IF EXISTS sessionID;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS disasters;

create table disasters
(
  id INT(8) AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(32) NOT NULL,
  date TIMESTAMP,
  latitude REAL(6,6),
  longitude REAL(6,6)
);

CREATE TABLE users
(
  id INT(8) AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(32) NOT NULL,
  lastName VARCHAR(32) NOT NULL,
  email VARCHAR(64),
  password VARCHAR(1024),
  dateOfBirth DATE,
  phoneNumber VARCHAR(64)
);

CREATE TABLE sessionID
(
  id VARCHAR(64) PRIMARY KEY NOT NULL,
  userID INT(8) NOT NULL,
  expiringDate TIMESTAMP NOT NULL,
  CONSTRAINT
    FOREIGN KEY (userID)
      REFERENCES users(id)
);



DROP TRIGGER IF EXISTS insertTime;


CREATE TRIGGER insertTime
  BEFORE INSERT ON sessionID
    FOR EACH ROW
      SET NEW.expiringDate = adddate(NOW(), 1);