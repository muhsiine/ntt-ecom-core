INSERT INTO CATEGORY(CATEGORY_ID, NAME) VALUES (1, 'Style & Fashion');
INSERT INTO CATEGORY(CATEGORY_ID, NAME) VALUES (2, 'Electronics');
INSERT INTO CATEGORY(CATEGORY_ID, NAME) VALUES (3, 'Health & Fitness');

INSERT INTO COUNTRY(COUNTRY_ID, NAME) VALUES (1, 'Morocco');

INSERT INTO CITY(CITY_ID, NAME, COUNTRY_ID) VALUES (1, 'Tetouan', 1);
INSERT INTO CITY(CITY_ID, NAME, COUNTRY_ID) VALUES (2, 'Larache', 1);

INSERT INTO STATUS(STATUS_ID, STATUS_DESC) VALUES ( 1, 'Pending');

INSERT INTO ADDRESS(ADDRESS_ID, ADDRESS_DESC, ZIP_CODE, CITY_ID)
    VALUES (1, 'ADD 1', '92000', 2);
INSERT INTO ADDRESS(ADDRESS_ID, ADDRESS_DESC, ZIP_CODE, CITY_ID)
    VALUES (2, 'ADD 2', '93000', 1);

INSERT INTO PRODUCT(PRODUCT_ID, CREATED_AT, DELETED_AT, DESCRIPTION, MODIFIED_AT,NAME, NM_AVAILABLE_ITEMS, PRICE, CATEGORY_ID)
VALUES ( 1, SYSDATE, null, 'PRDODUCT 1 DESC', SYSDATE, 'Product 1', 14, '23', 1);
INSERT INTO PRODUCT(PRODUCT_ID, CREATED_AT, DELETED_AT, DESCRIPTION, MODIFIED_AT,NAME, NM_AVAILABLE_ITEMS, PRICE, CATEGORY_ID)
VALUES ( 2, SYSDATE, null, 'PRDODUCT 2 DESC', SYSDATE, 'Product 2', 10, '23', 2);
INSERT INTO PRODUCT(PRODUCT_ID, CREATED_AT, DELETED_AT, DESCRIPTION, MODIFIED_AT,NAME, NM_AVAILABLE_ITEMS, PRICE, CATEGORY_ID)
VALUES ( 3, SYSDATE, null, 'PRDODUCT 3 DESC', SYSDATE, 'Product 3', 25, '23', 3);
INSERT INTO PRODUCT(PRODUCT_ID, CREATED_AT, DELETED_AT, DESCRIPTION, MODIFIED_AT,NAME, NM_AVAILABLE_ITEMS, PRICE, CATEGORY_ID)
VALUES ( 4, SYSDATE, null, 'PRDODUCT 4 DESC', SYSDATE, 'Product 4', 15, '23', 1);

INSERT INTO PRODUCT_IMAGE(IMAGE_ID, IMAGE_URL, PRODUCT_ID)
    VALUES(1, 'https://image.shutterstock.com/image-photo/product-light-bulb-word-cloud-600w-1664441488.jpg', 1);

INSERT INTO PRODUCT_IMAGE(IMAGE_ID, IMAGE_URL, PRODUCT_ID)
VALUES(2, 'https://image.shutterstock.com/image-illustration/wooden-product-display-podium-blurred-600w-1935942262.jpg', 2);

INSERT INTO PRODUCT_IMAGE(IMAGE_ID, IMAGE_URL, PRODUCT_ID)
VALUES(3, 'https://image.shutterstock.com/image-photo/shower-supplies-composition-cosmetic-products-600w-1446691724.jpg', 2);


INSERT INTO ORDERS(ORDER_ID, CREATED_AT, MODIFIED_AT, ADDRESS_ID, STATUS_ID)
    VALUES(1, SYSDATE, SYSDATE, 1, 1);

INSERT INTO ORDERS(ORDER_ID, CREATED_AT, MODIFIED_AT, ADDRESS_ID, STATUS_ID)
    VALUES(2, SYSDATE, SYSDATE, 2, 1);



INSERT INTO ORDER_ITEM(ID, QUANTITY, ORDER_ID, PRODUCT_ID)
    VALUES (1, 3, 1, 1);

INSERT INTO ORDER_ITEM(ID, QUANTITY, ORDER_ID, PRODUCT_ID)
VALUES (2, 2, 1, 2);

INSERT INTO ORDER_ITEM(ID, QUANTITY, ORDER_ID, PRODUCT_ID)
VALUES (3, 5, 2, 1);
INSERT INTO ORDER_ITEM(ID, QUANTITY, ORDER_ID, PRODUCT_ID)
VALUES (4, 1, 2, 3);
INSERT INTO ORDER_ITEM(ID, QUANTITY, ORDER_ID, PRODUCT_ID)
VALUES (5, 7, 2, 4);
