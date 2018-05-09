DROP TABLE IF EXISTS sessionID;

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  id INT(8) AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(32) NOT NULL,
  lastName VARCHAR(32) NOT NULL,
  email VARCHAR(64),
  password VARCHAR(1024)
);

CREATE TABLE sessionID
(
  id INT(8) AUTO_INCREMENT PRIMARY KEY,
  userID INT(8),
  expiringDate TIMESTAMP,
  FOREIGN KEY (userID)
    REFERENCES users(id)
  ON DELETE CASCADE
);

DROP TRIGGER IF EXISTS insertTime;

CREATE TRIGGER insertTime
  BEFORE INSERT ON sessionID
    FOR EACH ROW
      SET NEW.expiringDate = adddate(NOW(), 1);

/*INSERT INTO users(firstName, lastName, email, password) VALUE ('Martincu', 'Petru', 'martincu.petru@gmail.com', '12345');
INSERT INTO sessionID(userID) VALUE (1);
SELECT * FROM sessionID;
SELECT * FROM users;*/