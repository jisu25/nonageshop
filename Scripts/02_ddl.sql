/* 상품 */
DROP TABLE PRODUCT 
	CASCADE CONSTRAINTS;

/* 회원 */
DROP TABLE MEMBER 
	CASCADE CONSTRAINTS;

/* 장바구니 */
DROP TABLE CART 
	CASCADE CONSTRAINTS;

/* 주문상세 */
DROP TABLE ORDER_DETAIL 
	CASCADE CONSTRAINTS;

/* 주문 */
DROP TABLE ORDERS
	CASCADE CONSTRAINTS;

/* QNA 게시판 */
DROP TABLE QNA 
	CASCADE CONSTRAINTS;

/* 주소 */
DROP TABLE ADDRESS 
	CASCADE CONSTRAINTS;

/* 관리자 */
DROP TABLE WORKER 
	CASCADE CONSTRAINTS;

/* 상품 */
CREATE TABLE PRODUCT (
	product_no NUMBER(5) PRIMARY KEY, /* 상품번호 */
	product_name VARCHAR2(100), /* 상품명 */
	product_kind CHAR(1), /* 상품종류 */
	price NUMBER(7), /* 원가 */
	sale NUMBER(7), /* 판매가 */
	margin NUMBER(7), /* 수익 */
	product_content VARCHAR2(1000), /* 상품내용 */
	product_image VARCHAR2(50) DEFAULT 'default.jpg', /* 상품이미지 */
	del_yn CHAR(1) DEFAULT 'n', /* 상품삭제여부 */
	best_yn CHAR(1) DEFAULT 'n', /* 베스트상품여부 */
	reg_date DATE DEFAULT sysdate /* 등록일 */
);

ALTER TABLE PRODUCT
MODIFY best_yn DEFAULT 'n';

/* 회원 */
CREATE TABLE MEMBER (
	member_id VARCHAR2(20) PRIMARY KEY, /* 회원아이디 */
	member_pwd VARCHAR2(20), /* 회원암호 */
	member_name VARCHAR2(40), /* 회원이름 */
	member_email VARCHAR2(40), /* 회원이메일 */
	zip_num CHAR(7), /* 우편번호 */
	address VARCHAR2(100), /* 주소 */
	phone VARCHAR2(13), /* 전화번호 */
	del_yn CHAR(1) DEFAULT 'n', /* 탈퇴여부 */
	reg_date DATE DEFAULT sysdate /* 가입일 */
);


/* 장바구니 */
CREATE TABLE CART (
	cart_no NUMBER(5) PRIMARY KEY, /* 장바구니번호 */
	member_id VARCHAR2(20), /* 회원아이디 */
	product_no NUMBER(5) /* 상품번호 */
);


/* 주문상세 */
CREATE TABLE ORDER_DETAIL (
	order_detail_no NUMBER(5) PRIMARY KEY, /* 주문상세번호 */
	orders_no NUMBER(5), /* 주문번호 */
	product_no NUMBER(5), /* 상품번호 */
	order_quantity NUMBER(5), /* 주문수량 */
	res_yn CHAR(1) DEFAULT '1' /* 처리완료여부 */ -- 미처리 1, 처리 2
);



/* 주문 */
CREATE TABLE ORDERS (
	orders_no NUMBER(5) PRIMARY KEY, /* 주문번호 */
	member_id VARCHAR2(20), /* 주문자아이디 */
	orders_date DATE DEFAULT sysdate /* 주문일 */
);


/* QNA 게시판 */
-- NO SEQUENCE
CREATE TABLE QNA (
	qna_no NUMBER(5) PRIMARY KEY, /* 문의번호 */
	subject VARCHAR2(30), /* 제목 */
	qna_content VARCHAR2(1000), /* 문의내용 */
	reply VARCHAR2(1000), /* 답변내용 */
	member_id VARCHAR2(20), /* 작성자아이디 */
	res_yn CHAR(1) DEFAULT '1', /* 답변유무 */ -- 미처리 1, 처리 2
	reg_date DATE DEFAULT sysdate /* 작성일 */
);


/* 주소 */
CREATE TABLE ADDRESS (
	zip_num CHAR(5), /* 우편번호 */
	sido VARCHAR2(40), /* 시도 */
	gugun VARCHAR2(40), /* 구군 */
	dong VARCHAR2(40), /* 동 */
	zip_code VARCHAR2(40), /* 우편코드 */
	bunji VARCHAR2(40) /* 번지 */
);

/* 관리자 */
CREATE TABLE WORKER (
	id VARCHAR2(20) PRIMARY KEY, /* 관리자아이디 */
	pwd VARCHAR2(20), /* 암호 */
	name VARCHAR2(40), /* 이름 */
	phone VARCHAR2(13) /* 전화번호 */
);


/* FK */

ALTER TABLE CART
	ADD
		CONSTRAINT FK_MEMBER_TO_CART
		FOREIGN KEY (
			member_id
		)
		REFERENCES MEMBER (
			member_id
		);

ALTER TABLE CART
	ADD
		CONSTRAINT FK_PRODUCT_TO_CART
		FOREIGN KEY (
			product_no
		)
		REFERENCES PRODUCT (
			product_no
		);

ALTER TABLE ORDER_DETAIL
	ADD
		CONSTRAINT FK_ORDER_TO_ORDER_DETAIL
		FOREIGN KEY (
			orders_no
		)
		REFERENCES ORDERS (
			orders_no
		);

ALTER TABLE ORDER_DETAIL
	ADD
		CONSTRAINT FK_PRODUCT_TO_ORDER_DETAIL
		FOREIGN KEY (
			product_no
		)
		REFERENCES PRODUCT (
			product_no
		);

ALTER TABLE ORDERS
	ADD
		CONSTRAINT FK_MEMBER_TO_ORDER
		FOREIGN KEY (
			member_id
		)
		REFERENCES MEMBER (
			member_id
		);