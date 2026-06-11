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
