DELIMITER //
CREATE PROCEDURE sp_intern_search_by_name(IN p_name VARCHAR(50), IN p_page INT, IN p_size INT)
BEGIN
    DECLARE offset INT;
    SET offset = (p_page - 1) * p_size;
SELECT i.* FROM intern i
    INNER JOIN user u ON i.id_user = u.id_user
WHERE u.name LIKE CONCAT('%', p_name, '%')
    LIMIT offset, p_size;
END //
DELIMITER ;

DELIMITER ;

DELIMITER //
CREATE PROCEDURE sp_intern_find_by_area(IN p_area VARCHAR(25), IN p_page INT, IN p_size INT)
BEGIN
    DECLARE offset INT;
    SET offset = (p_page - 1) * p_size;
SELECT i.* FROM intern i
    INNER JOIN area a ON i.id_area = a.id_area
WHERE a.name = p_area
    LIMIT offset, p_size;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE sp_intern_find_by_university(IN p_university VARCHAR(100), IN p_page INT, IN p_size INT)
BEGIN
    DECLARE offset INT;
    SET offset = (p_page - 1) * p_size;
SELECT i.* FROM intern i
    INNER JOIN university u ON i.id_university = u.id_university
WHERE u.name = p_university
    LIMIT offset, p_size;
END //
DELIMITER ;
