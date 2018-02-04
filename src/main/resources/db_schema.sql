CREATE TABLE faculty
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR
);
CREATE TABLE faculty_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE specialty
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR,
    facultyid INT
);
CREATE TABLE specialty_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE students
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    phone VARCHAR,
    startyear VARCHAR NOT NULL,
    specialtyid INT
);
CREATE TABLE students_id_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
ALTER TABLE specialty ADD FOREIGN KEY (facultyid) REFERENCES faculty (id);
ALTER TABLE students ADD FOREIGN KEY (specialtyid) REFERENCES specialty (id);
CREATE INDEX fki_students_fk ON students (specialtyid);
