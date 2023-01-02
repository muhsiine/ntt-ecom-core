INSERT INTO USERS(ID, USERNAME, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, CREATED_AT, IMAGE_URL)
    VALUES (1, 'user1', 'user1_F', 'user1_L', 'user1@mail.com', '0612345678', SYSDATE, 'https://www.nicepng.com/png/full/128-1280406_view-user-icon-png-user-circle-icon-png.png');
INSERT INTO USER(ID, USERNAME, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, CREATED_AT, IMAGE_URL)
    VALUES (2, 'user2', 'user2_F', 'user2_L', 'user2@mail.com', '0687654321', SYSDATE, 'https://www.nicepng.com/png/full/128-1280406_view-user-icon-png-user-circle-icon-png.png');


INSERT INTO CATEGORIES(ID, CATEGORY_CODE, ICON) VALUES (1, 'FSN', '/path1');
INSERT INTO CATEGORIES(ID, CATEGORY_CODE, ICON) VALUES (2, 'ELC', '/path1');
INSERT INTO CATEGORIES(ID, CATEGORY_CODE, ICON) VALUES (3, 'HAF', '/path1');

INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (1, 'EN', 'Fashion', 1);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (2, 'EN', 'Electronics', 2);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (3, 'EN', 'Health & Fitness', 3);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (4, 'AR', 'الموضة', 1);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (5, 'AR', 'إلكترونيات', 2);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (6, 'AR', 'صحة واللياقة', 3);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (7, 'FR', 'Mode', 1);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (8, 'FR', 'Électronique', 2);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (9, 'FR', 'Santé et bien-être', 3);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (10, 'ES', 'Moda', 1);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (11, 'ES', 'Electrónica', 2);
INSERT INTO CATEGORY_X_LANG(ID, LANG_CODE, DESCRIPTION, CATEGORY_ID) VALUES (12, 'ES', 'Salud y Estado Fisico', 3);


INSERT INTO COUNTRY(ID, NAME) VALUES (1, 'Morocco');

INSERT INTO CITY(ID, NAME, COUNTRY_ID) VALUES (1, 'Tetouan', 1);
INSERT INTO CITY(ID, NAME, COUNTRY_ID) VALUES (2, 'Larache', 1);

INSERT INTO STATUS(ID, STATUS_DESC) VALUES ( 1, 'Pending');

INSERT INTO ADDRESS(ID, ADDRESS_DESC, ZIP_CODE, CITY_ID, USER_ID)
    VALUES (1, 'ADD 1', '92000', 2, 1);
INSERT INTO ADDRESS(ID, ADDRESS_DESC, ZIP_CODE, CITY_ID, USER_ID)
    VALUES (2, 'ADD 2', '93000', 1, 2);

INSERT INTO PRODUCT(ID, CREATED_AT, DELETED_AT, DESCRIPTION, MODIFIED_AT,NAME, NM_AVAILABLE_ITEMS, PRICE, CATEGORY_ID)
VALUES ( 1, SYSDATE-1, null, 'PRODUCT 1 DESC', SYSDATE, 'Product 1', 14, '23', 1);
INSERT INTO PRODUCT(ID, CREATED_AT, DELETED_AT, DESCRIPTION, MODIFIED_AT,NAME, NM_AVAILABLE_ITEMS, PRICE, CATEGORY_ID)
VALUES ( 2, SYSDATE+1, null, 'PRODUCT 2 DESC', SYSDATE, 'Product 2', 10, '18', 2);
INSERT INTO PRODUCT(ID, CREATED_AT, DELETED_AT, DESCRIPTION, MODIFIED_AT,NAME, NM_AVAILABLE_ITEMS, PRICE, CATEGORY_ID)
VALUES ( 3, SYSDATE, null, 'PRODUCT 3 DESC', SYSDATE, 'Product 3', 25, '26', 3);
INSERT INTO PRODUCT(ID, CREATED_AT, DELETED_AT, DESCRIPTION, MODIFIED_AT,NAME, NM_AVAILABLE_ITEMS, PRICE, CATEGORY_ID)
VALUES ( 4, SYSDATE, null, 'PRODUCT 4 DESC', SYSDATE, 'Product 4', 15, '50', 1);

INSERT INTO PRODUCT_IMAGE(ID, IMAGE_URL, PRODUCT_ID)
    VALUES(1, 'https://image.shutterstock.com/image-photo/product-light-bulb-word-cloud-600w-1664441488.jpg', 1);

INSERT INTO PRODUCT_IMAGE(ID, IMAGE_URL, PRODUCT_ID)
VALUES(2, 'https://image.shutterstock.com/image-illustration/wooden-product-display-podium-blurred-600w-1935942262.jpg', 2);

INSERT INTO PRODUCT_IMAGE(ID, IMAGE_URL, PRODUCT_ID)
VALUES(3, 'https://image.shutterstock.com/image-photo/shower-supplies-composition-cosmetic-products-600w-1446691724.jpg', 2);


INSERT INTO ORDERS(ID, CREATED_AT, MODIFIED_AT, ADDRESS_ID, STATUS_ID, USER_ID)
    VALUES(1, SYSDATE, SYSDATE, 1, 1, 1);

INSERT INTO ORDERS(ID, CREATED_AT, MODIFIED_AT, ADDRESS_ID, STATUS_ID, USER_ID)
    VALUES(2, SYSDATE, SYSDATE, 2, 1, 1);



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

INSERT INTO CART(ID, USER_ID) VALUES (1, 1);
INSERT INTO CART(ID, USER_ID) VALUES (2, 2);

INSERT INTO CART_ITEM(ID, CART_ID, PRODUCT_ID, QUANTITY) VALUES (1, 1, 1, 1);
INSERT INTO CART_ITEM(ID, CART_ID, PRODUCT_ID, QUANTITY) VALUES (2, 1, 2, 2);
INSERT INTO CART_ITEM(ID, CART_ID, PRODUCT_ID, QUANTITY) VALUES (3, 2, 3, 3);
INSERT INTO CART_ITEM(ID, CART_ID, PRODUCT_ID, QUANTITY) VALUES (4, 2, 4, 4);
