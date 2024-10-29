DELIMITER //

CREATE PROCEDURE sp_user_find_by_dni(IN p_dni VARCHAR(8))
BEGIN
SELECT * FROM user
WHERE dni = p_dni;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_user_search_by_role(IN p_role VARCHAR(20), IN page INT, IN size INT)
BEGIN
    DECLARE offset INT;
    SET offset = (page - 1) * size;
SELECT u.* FROM user u
                    INNER JOIN role r ON u-id_role = r.id_role
WHERE r.name = p_role LIMIT offset, size;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_user_search_by_name(IN p_name VARCHAR(8), IN page INT, IN size INT)
BEGIN
	DECLARE offset INT;
    SET offset = (page - 1) * size;
SELECT * FROM user WHERE name LIKE CONCAT('%', p_name, '%') LIMIT offset, size;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_user_search_by_dni(IN p_dni VARCHAR(8), IN page INT, IN size INT)
BEGIN
	DECLARE offset INT;
    SET offset = (page - 1) * size;
SELECT * FROM user WHERE dni LIKE CONCAT('%', p_dni, '%') LIMIT offset, size;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_user_find_by_status(IN p_status BOOLEAN, IN page INT, IN size INT)
BEGIN
    DECLARE offset INT;
    SET offset = (page - 1) * size;
SELECT * FROM user WHERE status = p_status LIMIT offset, size;
END //

DELIMITER ;