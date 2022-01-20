CREATE TABLE IF NOT EXISTS abiturient
(     id                       INT AUTO_INCREMENT PRIMARY KEY,
      first_name               VARCHAR(20)        NOT NULL,
      last_name                VARCHAR(20)        NOT NULL,
      phone_number             VARCHAR(12)        NOT NULL,
      password                VARCHAR(20)        NOT NULL
);
INSERT INTO abiturient (first_name, last_name, phone_number, password)
VALUES ('Yaroslav', 'Hurniak', '380682079553', '$2a$10$TrSlHkrApIxexkkl.SEpj.LlrEtJmEAxsNw/4SegDLghIsDlesh/2');
