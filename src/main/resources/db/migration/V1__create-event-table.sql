CREATE EXTENSION IF NOT EXISTS "pgcrypto";


CREATE TABLE events (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    img_url VARCHAR(255) NOT NULL,
    event_url VARCHAR(255) NOT NULL,
    date TIMESTAMP NOT NULL,
    remote BOOLEAN NOT NULL
);