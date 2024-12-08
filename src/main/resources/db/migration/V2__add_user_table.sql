CREATE TABLE users(
        id BIGINT primary key generated always as identity,
        username VARCHAR(150),
        password VARCHAR(500)
);
INSERT INTO users(username, password)
VALUES ('user', '$2a$10$Y2hl9k1TH5GQS26fycxih.jayQhfBLzEj17yNhZxWp/ZjM0x2ck1u');