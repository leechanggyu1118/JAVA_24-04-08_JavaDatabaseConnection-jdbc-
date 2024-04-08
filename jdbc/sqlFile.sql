--유저 생성구문
CREAT user 'javaUser'@'localhost' indentified by 'mysql';

--DB 생성
CREATE DATABASE javadb;

--유저 권한 부여
GRANT ALL PRIVILEGES ON javadb.* TO 'javaUser'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- 상품등록 테이블 생성
CREATE TABLE product(
pno INT NOT NULL AUTO_INCREMENT,
pname VARCHAR(50) NOT NULL,
price INT NOT NULL DEFAULT 0,
regdate DATETIME DEFAULT NOW(),
madeby TEXT,
PRIMARY KEY(pno));