CREATE TABLE IF NOT EXISTS Users
(
    LOGIN       TEXT    PRIMARY KEY NOT NULL,
    PASSWORD    TEXT                NOT NULL
);

CREATE TABLE IF NOT EXISTS Accounts
(
    ID                INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    USER_LOGIN        TEXT                NOT NULL,
    DESCRIPTION       TEXT                NOT NULL
);

CREATE TABLE IF NOT EXISTS Records
(
    ACCOUNT_ID                  INT                  NOT NULL,
    OPERATION_AMOUNT            REAL                 NOT NULL,
    OPERATION_TYPE              TEXT                 NOT NULL,
    DESCRIPTION                 TEXT                 NOT NULL,
    CATEGORY_ID                 INT                  NOT NULL,
    DATE                        TEXT                 NOT NULL
);

CREATE TABLE IF NOT EXISTS Categories
(
    ID              INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    CATEGORY_NAME   TEXT                NOT NULL
);