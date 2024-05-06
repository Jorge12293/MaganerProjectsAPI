-- Eliminar todos los registros de la tabla tasks
DELETE FROM users;

-- Eliminar todos los registros de la tabla tasks
DELETE FROM tasks;

-- Reiniciar el contador de identidad de la tabla tasks
ALTER TABLE tasks ALTER COLUMN task_id RESTART WITH 1;

-- Eliminar todos los registros de la tabla projects
DELETE FROM projects;

-- Reiniciar el contador de identidad de la tabla projects
ALTER TABLE projects ALTER COLUMN project_id RESTART WITH 1;

-- Datos de prueba para la tabla tasks
INSERT INTO users (uid, username, email) VALUES
('123456789','user1','user1@gmail.com'),
('64578635478as463','user2','user2@gmail.com'),
('65748326745sad83','user3','user3@gmail.com'),
('65748326745asd83','user4','user4@gmail.com'),
('6574832674asd583','user5','user5@gmail.com'),
('65748326745sad83','user6','user6@gmail.com'),
('6574832674sad583','user7','user7@gmail.com'),
('65748326745asd83','user8','user8@gmail.com'),
('6574832674asd583','user9','user9@gmail.com'),
('657483267asd4583','user10','user10@gmail.com'),
('657483267458sad3','user11','user11@gmail.com'),
('65748326745sad83','user12','user12@gmail.com'),
('65748326745asd83','user13','user13@gmail.com'),
('6574832674asd583','user14','user14@gmail.com'),
('65748326dsa74583','user15','user15@gmail.com'),
('65748326asd74583','user16','user16@gmail.com'),
('6574832674asd583','user17','user17@gmail.com'),
('4389276234sad2309','user18','user18@gmail.com');

-- Datos de prueba para la tabla projects
INSERT INTO projects (name, description) VALUES
('Proyecto A', 'Descripción del proyecto A'),
('Proyecto B', 'Descripción del proyecto B'),
('Proyecto C', 'Descripción del proyecto C'),
('Proyecto D', 'Descripción del proyecto D'),
('Proyecto E', 'Descripción del proyecto E'),
('Proyecto F', 'Descripción del proyecto F'),
('Proyecto G', 'Descripción del proyecto G'),
('Proyecto H', 'Descripción del proyecto H'),
('Proyecto I', 'Descripción del proyecto I'),
('Proyecto J', 'Descripción del proyecto J'),
('Proyecto K', 'Descripción del proyecto Q'),
('Proyecto L', 'Descripción del proyecto K'),
('Proyecto M', 'Descripción del proyecto L'),
('Proyecto N', 'Descripción del proyecto M'),
('Proyecto O', 'Descripción del proyecto N'),
('Proyecto P', 'Descripción del proyecto O'),
('Proyecto Q', 'Descripción del proyecto P'),
('Proyecto R', 'Descripción del proyecto Q'),
('Proyecto S', 'Descripción del proyecto R'),
('Proyecto T', 'Descripción del proyecto S'),
('Proyecto U', 'Descripción del proyecto T');


-- Datos de prueba para la tabla tasks
INSERT INTO tasks (title, description, status, project_id,user_id) VALUES
('Tarea 1', 'Descripción de la tarea 1', 'TODO', 1,1),
('Tarea 2', 'Descripción de la tarea 2', 'IN_PROGRESS', 1,  NULL),
('Tarea 3', 'Descripción de la tarea 3', 'TODO', 2,NULL),
('Tarea 4', 'Descripción de la tarea 4', 'COMPLETED',3 ,1);


