-- Очистка таблиц (опционально)
DELETE FROM task_categories;
DELETE FROM tasks;
DELETE FROM categories;

-- Примеры задач
INSERT INTO tasks (title, description, priority, status, due_date) VALUES
('Изучить Git ветвление', 'Создать ветку feature, сделать merge и решить конфликт', 'high', 'pending', '2026-06-05'),
('Написать Hello World на Java', 'Базовая программа для знакомства с Java', 'medium', 'completed', '2026-06-03'),
('Создать калькулятор', 'Сложение, вычитание, умножение, деление', 'medium', 'pending', '2026-06-04'),
('Подключить SQLite к Java', 'Использовать JDBC для работы с БД', 'high', 'pending', '2026-06-10');

-- Категории
INSERT INTO categories (name) VALUES 
('Обучение'), 
('Разработка'), 
('Git'), 
('Java'),
('SQLite');

-- Связи задач с категориями
INSERT INTO task_categories (task_id, category_id) VALUES
(1, 1), (1, 3),  -- Задача 1: Обучение + Git
(2, 1), (2, 4),  -- Задача 2: Обучение + Java
(3, 2), (3, 4),  -- Задача 3: Разработка + Java
(4, 2), (4, 5);  -- Задача 4: Разработка + SQLite

-- Пример запроса: все задачи с категориями
SELECT 
    t.id,
    t.title, 
    t.priority, 
    t.status,
    GROUP_CONCAT(c.name) as categories
FROM tasks t
LEFT JOIN task_categories tc ON t.id = tc.task_id
LEFT JOIN categories c ON tc.category_id = c.id
GROUP BY t.id;