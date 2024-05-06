-- Eliminar la tabla 'users'
DROP TABLE IF EXISTS users;
-- Eliminar la tabla 'projects'
DROP TABLE IF EXISTS projects;
-- Eliminar la tabla 'tasks'
DROP TABLE IF EXISTS tasks;

-- Tabla users
CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    uid VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Tabla projects
CREATE TABLE IF NOT EXISTS projects (
    project_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

-- Tabla tasks
CREATE TABLE IF NOT EXISTS tasks (
    task_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    user_id BIGINT NULL,
    project_id BIGINT,
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE
);

