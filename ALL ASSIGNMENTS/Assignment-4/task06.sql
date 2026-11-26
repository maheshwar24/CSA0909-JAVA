CREATE DATABASE IF NOT EXISTS csa09_assignment4;
USE csa09_assignment4;

CREATE TABLE IF NOT EXISTS events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    event_name VARCHAR(120) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS registrations (
    registration_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id VARCHAR(30) NOT NULL,
    student_name VARCHAR(120) NOT NULL,
    event_id INT NOT NULL,
    registered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_registration_event FOREIGN KEY (event_id) REFERENCES events(event_id),
    CONSTRAINT uq_student_event UNIQUE (student_id, event_id)
);

INSERT IGNORE INTO events(event_name) VALUES
('Cultural Fest'), ('Tech Symposium'), ('Sports Day'), ('Music Night');

-- Task 1 fee-payment table
CREATE TABLE IF NOT EXISTS fees (
    fee_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    reg_no VARCHAR(30) NOT NULL,
    amount DECIMAL(10,2) NOT NULL CHECK (amount > 0),
    paid_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
