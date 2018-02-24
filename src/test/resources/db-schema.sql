SET MODE POSTGRESQL;

CREATE TABLE IF NOT EXISTS country (
  id        IDENTITY,
  name      VARCHAR(255),
  code_name VARCHAR(255)
);