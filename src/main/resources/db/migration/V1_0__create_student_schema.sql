CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id INTEGER,
    role_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE SEQUENCE seq_for_trigger
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS student
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER,
    first_name VARCHAR,
    last_name  VARCHAR,
    patronymic VARCHAR,
    email      VARCHAR,
    phone      VARCHAR,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS company
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER,
    name        VARCHAR,
    description VARCHAR,
    email       VARCHAR,
    address     VARCHAR,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS internship
(
    id          SERIAL PRIMARY KEY,
    company_id  INTEGER,
    name        VARCHAR,
    description VARCHAR,
    url         VARCHAR,
    responses   INTEGER,
    FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS students_internships
(
    student_id    INTEGER,
    internship_id INTEGER,
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (internship_id) REFERENCES internship (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tag
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS internships_tags
(
    internship_id INTEGER,
    tag_id        INTEGER,
    FOREIGN KEY (internship_id) REFERENCES internship (id),
    FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE
);

INSERT INTO roles
VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles
VALUES (2, 'ROLE_STUDENT');
INSERT INTO roles
VALUES (3, 'ROLE_COMPANY');
INSERT INTO users
VALUES (1, 'admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.');

CREATE OR REPLACE FUNCTION user_student() RETURNS TRIGGER AS
$user_student$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        IF (NEW.role_id = 2) THEN
            INSERT INTO student VALUES (nextval('seq_for_trigger'), NEW.user_id, NULL, NULL);
        ELSIF (NEW.role_id = 3) THEN
            INSERT INTO company VALUES (nextval('seq_for_trigger'), NEW.user_id, NULL, NULL);
        END IF;
        RETURN NEW;
    END IF;
    RETURN NULL; -- возвращаемое значение для триггера AFTER игнорируется
END
$user_student$ LANGUAGE plpgsql;

CREATE TRIGGER user_student
    AFTER INSERT
    ON users_roles
    FOR EACH ROW
EXECUTE PROCEDURE user_student();

INSERT INTO users_roles
VALUES (1, 1);

INSERT INTO tag
VALUES (1, 'ALL');
INSERT INTO tag
VALUES (2, 'AUTOMOTIVE_BUSINESS');
INSERT INTO tag
VALUES (3, 'ADMINISTRATIVE_STAFF');
INSERT INTO tag
VALUES (4, 'SAFETY');
INSERT INTO tag
VALUES (5, 'TOP_MANAGEMENT');
INSERT INTO tag
VALUES (6, 'PURCHASES');
INSERT INTO tag
VALUES (7, 'INFORMATION_TECHNOLOGY');
INSERT INTO tag
VALUES (8, 'ART');
INSERT INTO tag
VALUES (9, 'ADVERTISING');
INSERT INTO tag
VALUES (10, 'MEDICINE');
INSERT INTO tag
VALUES (11, 'SALES');
INSERT INTO tag
VALUES (12, 'TOURISM');
INSERT INTO tag
VALUES (13, 'PERSONNEL_MANAGEMENT');
INSERT INTO tag
VALUES (14, 'LAWYERS');
INSERT INTO tag
VALUES (15, 'OTHER');



