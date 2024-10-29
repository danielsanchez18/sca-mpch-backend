DELIMITER //

CREATE PROCEDURE sp_role_search_by_name(IN p_name VARCHAR(20), IN page INT, IN size INT)
BEGIN
	DECLARE offset INT;
    SET offset = (page - 1) * size;
SELECT * FROM role WHERE name LIKE CONCAT('%', p_name, '%') LIMIT offset, size;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_role_find_by_name(IN p_name VARCHAR(20))
BEGIN
SELECT * FROM role
WHERE name = p_name;
END //

DELIMITER ;