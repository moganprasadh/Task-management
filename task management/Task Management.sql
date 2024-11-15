CREATE DATABASE TaskManagement;
USE TaskManagement;

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    role VARCHAR(50) DEFAULT 'User',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Projects (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    project_name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATETIME,
    end_date DATETIME,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE Tasks (
    task_id INT PRIMARY KEY,
    user_id INT,
    project_id INT,
    task_name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) DEFAULT 'Pending',
    deadline DATETIME,
    priority VARCHAR(20),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE SET NULL
);

DELIMITER //
CREATE PROCEDURE AddTask(
    IN p_user_id INT,
    IN p_project_id INT,
    IN p_task_name VARCHAR(255),
    IN p_description TEXT,
    IN p_status VARCHAR(50),
    IN p_deadline DATETIME,
    IN p_priority VARCHAR(20)
)
BEGIN
    INSERT INTO Tasks (user_id, project_id, task_name, description, status, deadline, priority, created_at, updated_at)
    VALUES (p_user_id, p_project_id, p_task_name, p_description, p_status, p_deadline, p_priority, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateTask(
    IN p_task_id INT,
    IN p_task_name VARCHAR(255),
    IN p_description TEXT,
    IN p_status VARCHAR(50),
    IN p_deadline DATETIME,
    IN p_priority VARCHAR(20)
)
BEGIN
    UPDATE Tasks
    SET task_name = p_task_name,
        description = p_description,
        status = p_status,
        deadline = p_deadline,
        priority = p_priority,
        updated_at = CURRENT_TIMESTAMP
    WHERE task_id = p_task_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteTask(
    IN p_task_id INT
)
BEGIN
    DELETE FROM Tasks
    WHERE task_id = p_task_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE MarkTaskAsComplete(
    IN p_task_id INT
)
BEGIN
    UPDATE Tasks
    SET status = 'Completed',
        updated_at = CURRENT_TIMESTAMP
    WHERE task_id = p_task_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SetTaskDeadline(
    IN p_task_id INT,
    IN p_deadline DATETIME
)
BEGIN
    UPDATE Tasks
    SET deadline = p_deadline,
        updated_at = CURRENT_TIMESTAMP
    WHERE task_id = p_task_id;
END //
DELIMITER ;

SET SQL_SAFE_UPDATES = 0;

INSERT INTO Users (username, email, password, first_name, last_name, role)
VALUES ('ram', 'ram@example.com', 'password123', 'ram', 'raj', 'User');

INSERT INTO Projects (user_id, project_name, description, start_date, end_date)
VALUES (1, 'Task Management', 'Description of Project', '2024-01-01 09:00:00', '2024-12-31 17:00:00');

CALL AddTask(1, 1, 'Task 1', 'Description for Task 1', 'Pending', '2024-11-30 17:00:00', 'High');
CALL UpdateTask(1, 'Updated Task 1', 'Updated description for Task 1', 'In Progress', '2024-12-15 17:00:00', 'Medium');
CALL DeleteTask(1);
CALL MarkTaskAsComplete(1);
CALL SetTaskDeadline(1, '2024-12-31 17:00:00');
SELECT * FROM Tasks;

ALTER TABLE Tasks
MODIFY COLUMN task_id INT AUTO_INCREMENT;



