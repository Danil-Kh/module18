CREATE TABLE users(
                      user_id BIGINT primary key generated always as identity,
                      username VARCHAR(150),
                      password VARCHAR(500)
);
INSERT INTO users(username, password)
VALUES ('user', '$2a$10$Y2hl9k1TH5GQS26fycxih.jayQhfBLzEj17yNhZxWp/ZjM0x2ck1u');

CREATE TABLE IF NOT EXISTS notes (
                                     id BIGINT PRIMARY KEY generated always as identity,
                                     user_id BIGINT NOT NULL,
                                     title VARCHAR(100) NOT NULL,
                                     content VARCHAR(1000) NOT NULL,
                                     FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);