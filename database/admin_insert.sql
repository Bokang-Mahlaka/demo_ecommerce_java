-- SQL query to insert an admin user record into the users table with admin role

INSERT INTO Users (username, encrypted_password, email, role_id, currency_preference)
VALUES ('admin', '$2a$10$7QJ9v1ZxQ9v1ZxQ9v1ZxQO7QJ9v1ZxQ9v1ZxQ9v1ZxQ9v1ZxQ9v1Zx', 'admin@example.com', 1, 'USD');

-- Note: The encrypted_password value is a bcrypt hash of a default password (e.g., "admin123").
-- Replace the hash with the actual bcrypt hash of your chosen admin password.
