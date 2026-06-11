CREATE TABLE customers (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           create_date DATE NOT NULL,
                           update_date DATE
);

CREATE TYPE project_status AS ENUM ('CREATED', 'WORKING', 'STANDBY', 'COMPLETED', 'CLOSED');

CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          description VARCHAR(255),
                          estimated_hours INT NOT NULL,
                          status project_status NOT NULL DEFAULT 'CREATED',
                          start_date DATE NOT NULL,
                          end_date DATE NOT NULL,
                          create_date DATE NOT NULL,
                          update_date DATE,
                          customer_id INT REFERENCES customers(id)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(64) NOT NULL,
                       surname VARCHAR(128) NOT NULL,
                       username VARCHAR(64) UNIQUE NOT NULL,
                       password VARCHAR(64) NOT NULL,
                       create_date DATE NOT NULL,
                       update_date DATE
);

CREATE TABLE activities (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(64) NOT NULL,
                            estimated_hours INT NOT NULL,
                            create_date DATE NOT NULL,
                            update_date DATE,
                            project_id INT REFERENCES projects(id)
);

CREATE TABLE users_activities(
                                 activity_id INT REFERENCES activities(id),
                                 user_id INT REFERENCES users(id),
                                 PRIMARY KEY (activity_id, user_id)
);









-- =========================
-- CUSTOMERS
-- =========================
INSERT INTO customers (name, create_date, update_date) VALUES
                                                           ('Acme Srl',        '2026-06-01', NULL),
                                                           ('Beta Consulting', '2026-06-01', NULL),
                                                           ('Gamma Tech',      '2026-06-01', NULL),
                                                           ('Delta Retail',    '2026-06-01', NULL),
                                                           ('Omega Logistics', '2026-06-01', NULL);

-- =========================
-- USERS
-- =========================
INSERT INTO users (name, surname, username, password, create_date, update_date) VALUES
                                                                                   ('Mario',  'Rossi',   'mrossi',   'password1', '2026-06-01', NULL),
                                                                                   ('Luca',   'Bianchi', 'lbianchi', 'password2', '2026-06-01', NULL),
                                                                                   ('Anna',   'Verdi',   'averdi',   'password3', '2026-06-01', NULL),
                                                                                   ('Sara',   'Neri',    'sneri',    'password4', '2026-06-01', NULL),
                                                                                   ('Paolo',  'Gialli',  'pgialli',  'password5', '2026-06-01', NULL);

-- =========================
-- PROJECTS
-- =========================
INSERT INTO projects (
    title, description, customer_id, estimated_hours, status,
    start_date, end_date, create_date, update_date
) VALUES
      ('CRM Aziendale',
       'Sviluppo di un CRM interno per gestione clienti',
       1, 320, 'CREATED', '2026-06-03', '2026-08-30', '2026-06-01', NULL),

      ('Portale HR',
       'Portale web per ferie, permessi e documenti dipendenti',
       2, 220, 'WORKING', '2026-06-10', '2026-09-15', '2026-06-01', NULL),

      ('E-commerce B2B',
       'Piattaforma ordini per clienti business',
       3, 450, 'STANDBY', '2026-06-15', '2026-11-30', '2026-06-01', NULL),

      ('App Magazzino',
       'Applicazione per inventario e movimentazione merci',
       5, 280, 'COMPLETED', '2026-06-20', '2026-10-10', '2026-06-01', NULL),

      ('Dashboard Vendite',
       'Dashboard analitica per monitoraggio KPI commerciali',
       4, 180, 'CLOSED', '2026-07-01', '2026-09-01', '2026-06-01', NULL);

-- =========================
-- ACTIVITIES
-- =========================
INSERT INTO activities (
    name, estimated_hours, project_id, create_date, update_date
) VALUES
      ('Analisi requisiti CRM',      40, 1, '2026-06-01', NULL),
      ('Sviluppo backend CRM',      140, 1, '2026-06-01', NULL),
      ('Sviluppo frontend CRM',      90, 1, '2026-06-01', NULL),
      ('Testing CRM',                50, 1, '2026-06-01', NULL),

      ('Analisi requisiti HR',       30, 2, '2026-06-01', NULL),
      ('Implementazione portale HR',140, 2, '2026-06-01', NULL),
      ('Collaudo portale HR',        50, 2, '2026-06-01', NULL),

      ('Analisi e-commerce',         60, 3, '2026-06-01', NULL),
      ('Backend e-commerce',        180, 3, '2026-06-01', NULL),
      ('Frontend e-commerce',       140, 3, '2026-06-01', NULL),
      ('Integrazione pagamenti',     70, 3, '2026-06-01', NULL),

      ('Analisi app magazzino',      40, 4, '2026-06-01', NULL),
      ('Sviluppo app magazzino',    170, 4, '2026-06-01', NULL),
      ('Test app magazzino',         70, 4, '2026-06-01', NULL),

      ('Raccolta KPI vendite',       30, 5, '2026-06-01', NULL),
      ('Sviluppo dashboard',        110, 5, '2026-06-01', NULL),
      ('Testing dashboard',          40, 5, '2026-06-01', NULL);

-- =========================
-- USERS_ACTIVITIES
-- =========================
INSERT INTO users_activities (activity_id, user_id) VALUES
      (1, 1),   -- Mario - Analisi requisiti CRM
      (1, 2),   -- Luca - Analisi requisiti CRM
      (2, 1),   -- Mario - Sviluppo backend CRM
      (2, 2),   -- Luca - Sviluppo backend CRM
      (3, 3),   -- Anna - Sviluppo frontend CRM
      (3, 4),   -- Sara - Sviluppo frontend CRM
      (4, 5),   -- Paolo - Testing CRM

      (5, 1),   -- Mario - Analisi requisiti HR
      (6, 2),   -- Luca - Implementazione portale HR
      (6, 3),   -- Anna - Implementazione portale HR
      (7, 4),   -- Sara - Collaudo portale HR

      (8, 1),   -- Mario - Analisi e-commerce
      (9, 2),   -- Luca - Backend e-commerce
      (9, 3),   -- Anna - Backend e-commerce
      (10, 4),  -- Sara - Frontend e-commerce
      (11, 5),  -- Paolo - Integrazione pagamenti

      (12, 1),  -- Mario - Analisi app magazzino
      (13, 2),  -- Luca - Sviluppo app magazzino
      (13, 3),  -- Anna - Sviluppo app magazzino
      (14, 4),  -- Sara - Test app magazzino

      (15, 5),  -- Paolo - Raccolta KPI vendite
      (16, 3),  -- Anna - Sviluppo dashboard
      (16, 4),  -- Sara - Sviluppo dashboard
      (17, 2);  -- Luca - Testing dashboard

