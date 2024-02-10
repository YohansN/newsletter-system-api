CREATE TABLE subscribers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL UNIQUE,
    signup_date DATE NOT NULL
);