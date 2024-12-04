CREATE TABLE notes(
                      id BIGINT primary key generated always as identity,
                      title VARCHAR(100),
                      content VARCHAR(750)
);
