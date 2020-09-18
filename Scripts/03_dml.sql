/* PRODUCT DATA */

insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE)
values('크로그다일부츠', '2', '40000', '50000', '10000', '오지니랄 크로그다일부츠 입니다.', 'w2.jpg');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, BEST_YN)
VALUES('롱부츠', '2', 40000, 50000, 10000,'따뜻한 롱부츠 입니다.', 'w-28.jpg', 'n');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, BEST_YN)
values('힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w-26.jpg', 'n');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, BEST_YN)
values('슬리퍼', '4', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w-25.jpg', 'y');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, BEST_YN)
values('회색힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w9.jpg', 'n');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE)
values('여성부츠', '2', '12000', '18000', '6000', '여성용 부츠', 'w4.jpg');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, BEST_YN)
values('핑크샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-10.jpg', 'y');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, BEST_YN)
values('슬리퍼', '3', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w11.jpg', 'y');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE)
values('스니커즈', '4', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w1.jpg');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, BEST_YN)
values('샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-09.jpg','n');
insert into product(PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, BEST_YN)
values('스니커즈', '5', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w-05.jpg','n');

SELECT * FROM product;


/* member */

insert into worker values('admin', 'admin', '홍관리', '010-777-7777');
insert into worker values('pinksung', 'pinksung', '명강사', '010-999-9696');

SELECT * FROM worker;

insert into member(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, ZIP_NUM, ADDRESS, PHONE) values
('one', '1111', '김나리', '133-110', '서울시성동구성수동1가 1번지21호', '017-777-7777');
insert into member(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, ZIP_NUM, ADDRESS, PHONE) values
('two', '2222', '이백합', '130-120', '서울시송파구잠실2동 리센츠 아파트 201동 505호', '011-123-4567');

SELECT * FROM MEMBER;


