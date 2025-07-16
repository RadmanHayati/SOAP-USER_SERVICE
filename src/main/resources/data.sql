INSERT INTO users (id, name, email)
VALUES (1, 'John Doe', 'john.doe@example.com'),
       (2, 'Jane Smith', 'jane.smith@example.com'),
       (3, 'Bob Johnson', 'bob.johnson@example.com') ON CONFLICT (email) DO NOTHING;