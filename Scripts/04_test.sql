INSERT INTO BOARD(NAME, EMAIL, PASS, TITLE, CONTENT) VALUES (?, ?, ?, ?, ?);
UPDATE BOARD SET NAME = '성윤정', EMAIL = 'pinksung@nate.com', PASS = '1234', TITLE = '첫방문', CONTENT = '반갑습니다' WHERE NUM = 1;
DELETE BOARD WHERE NUM = ?;

SELECT * FROM board;
