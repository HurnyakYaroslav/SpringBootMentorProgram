CREATE TABLE abiturient (
      id                       INT AUTO_INCREMENT PRIMARY KEY,
      first_name               VARCHAR(20)        NOT NULL,
      last_name                VARCHAR(20)        NOT NULL,
      phonenumber              VARCHAR(11)        UNIQUE
);
