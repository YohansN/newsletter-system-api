CREATE TABLE blog_post (
    id SERIAL PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    content TEXT NOT NULL,
    publication_date DATE NOT NULL,
    is_visible BOOLEAN NOT NULL
)