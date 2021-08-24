DROP TABLE IF EXISTS "web_sessions";
DROP TABLE IF EXISTS "messages";
DROP TABLE IF EXISTS "calls";

DROP TABLE IF EXISTS "cellphones";
DROP TABLE IF EXISTS "gadgets";

DROP TABLE IF EXISTS "tariffs";
DROP TABLE IF EXISTS "users";


CREATE TABLE IF NOT EXISTS users
(
    id      SERIAL PRIMARY KEY,
    name_id VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS tariffs
(
    id      SERIAL PRIMARY KEY,
    name_id VARCHAR(50),
    price   FLOAT
);

CREATE TABLE IF NOT EXISTS gadgets
(
    id      SERIAL PRIMARY KEY,
    type_id VARCHAR(100),
    user_id INTEGER REFERENCES users
);

CREATE TABLE IF NOT EXISTS cellphones
(
    id        SERIAL PRIMARY KEY,
    model     VARCHAR(100),
    number_id VARCHAR(100),
    tariff_id INTEGER REFERENCES tariffs,
    user_id   INTEGER REFERENCES users
);



CREATE TABLE IF NOT EXISTS calls
(
    id                SERIAL PRIMARY KEY,
    time_of_call      TIMESTAMP,
    call_duration_sec INTEGER,
    caller_id         INTEGER,
    receiver_id       INTEGER,
    cellphone_id      INTEGER REFERENCES cellphones
);

CREATE TABLE IF NOT EXISTS messages
(
    id           SERIAL PRIMARY KEY,
    time_of_send TIMESTAMP,
    content      VARCHAR(255),
    sender_id    INTEGER,
    receiver_id  INTEGER,
    cellphone_id INTEGER REFERENCES cellphones
);

CREATE TABLE IF NOT EXISTS web_sessions
(
    id                   SERIAL PRIMARY KEY,
    time_of_connection   TIMESTAMP,
    session_duration_sec INTEGER,
    traffic_kb           INTEGER,
    gadget_id            INTEGER REFERENCES gadgets,
    cellphone_id         INTEGER REFERENCES cellphones
);
