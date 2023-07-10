-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- default pass 123456
CREATE OR REPLACE PROCEDURE CREATE_ADMIN IS
BEGIN
    INSERT INTO USERS(ID,USERNAME,EMAIL,PASSWORD,NAME,FAMILY,ACCOUNT_NON_EXPIRED,ACCOUNT_NON_LOCKED,CREDENTIALS_NON_EXPIRED,ENABLED,CREATED_AT,LAST_UPDATE_AT)
    VALUES (1,'admin','ADMIN@TEST.COM','$2a$10$W5I2UnUIpbuwS4.N/roto.pjO9DoK0kWm98U.gkcaAXuDK44RlCKq','ADMIN','ADMIN',1,1,1,1,CURRENT_DATE,CURRENT_DATE);

    INSERT INTO USER_AUTHORITY(ID,AUTHORITY,CREATED_AT,CREATOR_ID,LAST_MODIFIER_ID,LAST_UPDATE_AT)
    VALUES (1, 'admin', CURRENT_DATE ,1 ,1 , CURRENT_DATE);

    INSERT INTO USER_AUTHORITY(ID,AUTHORITY,CREATED_AT,CREATOR_ID,LAST_MODIFIER_ID,LAST_UPDATE_AT)
    VALUES (2, 'user', CURRENT_DATE ,1 ,1 , CURRENT_DATE);

    INSERT INTO USER_AUTHORITY_USER (USER_AUTHORITY_ID, USER_ID) VALUES (1,1);
    INSERT INTO USER_AUTHORITY_USER (USER_AUTHORITY_ID, USER_ID) VALUES (2,1);
    COMMIT;

END;
/

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++

BEGIN
CREATE_ADMIN;
END;
/

-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

INSERT INTO ENUM_MESSAGE_TYPE (ID, TITLE)
VALUES (1, 'ایمیل');
       (2, 'پیامک');
-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++