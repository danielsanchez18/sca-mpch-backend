DELIMITER //

CREATE PROCEDURE `sp_area_search_by_name`(IN p_name VARCHAR(25), IN page INT, IN size INT)
BEGIN
	DECLARE offset INT;
    SET offset = (page - 1) * size;
SELECT * FROM area WHERE name LIKE CONCAT('%', p_name, '%') LIMIT offset, size;
END

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_area_find_by_name(IN p_name VARCHAR(25))
BEGIN
SELECT * FROM area
WHERE name = p_name;
END //

DELIMITER ;