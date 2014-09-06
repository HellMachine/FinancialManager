CREATE TABLE IF NOT EXISTS Users
(
    LOGIN        TEXT    PRIMARY KEY NOT NULL,
    PASSWORD     CHAR(32)            NOT NULL,
    USER_AVATAR  TEXT                NOT NULL
);

CREATE TABLE IF NOT EXISTS Accounts
(
    ACCOUNT_ID        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    ACCOUNT_NAME      TEXT                              NOT NULL,
    ACCOUNT_OWNER     TEXT                              NOT NULL,
    BALANCE           REAL                              NOT NULL,
    DESCRIPTION       TEXT                              NOT NULL
);

CREATE TABLE IF NOT EXISTS Records
(
    ACCOUNT_ID          INT   NOT NULL,
    OPERATION_AMOUNT    REAL  NOT NULL,
    IS_ADD_TYPE         INT   NOT NULL,
    DESCRIPTION         TEXT  NOT NULL,
    CATEGORY_ID         INT   NOT NULL,
    OPERATION_DATE      TEXT  NOT NULL
);

CREATE TABLE IF NOT EXISTS Categories
(
    ID              INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    CATEGORY_NAME   TEXT                              NOT NULL
);