CREATE TABLE address (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    uf VARCHAR(50) NOT NULL,
    events_id UUID,
    FOREIGN KEY (events_id) REFERENCES events(id) ON DELETE CASCADE
);