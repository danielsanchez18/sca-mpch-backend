DELIMITER //

CREATE PROCEDURE sp_university_search_by_name(IN p_name VARCHAR(100), IN page INT, IN size INT)
BEGIN
	DECLARE offset INT;
    SET offset = (page - 1) * size;
SELECT * FROM university WHERE name LIKE CONCAT('%', p_name, '%') LIMIT offset, size;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_university_find_by_name(IN p_name VARCHAR(100))
BEGIN
SELECT * FROM university
WHERE name = p_name;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_university_find_by_acronym(IN p_acronym VARCHAR(10))
BEGIN
SELECT * FROM university
WHERE acronym = p_acronym;
END //

DELIMITER ;