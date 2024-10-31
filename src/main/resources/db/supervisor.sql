DELIMITER //

CREATE PROCEDURE sp_supervisor_search_by_name(
    IN p_name VARCHAR(50),
    IN p_page INT,
    IN p_size INT
)
BEGIN
    DECLARE offset INT;
    SET offset = (p_page - 1) * p_size;

SELECT s.*
FROM supervisor s
         INNER JOIN user u ON s.id_user = u.id_user
WHERE u.name LIKE CONCAT('%', p_name, '%')
    LIMIT offset, p_size;

END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_supervisor_find_by_area(
    IN p_area VARCHAR(25),
    IN p_page INT,
    IN p_size INT
)
BEGIN
    DECLARE offset INT;
    SET offset = (p_page - 1) * p_size;

SELECT s.*
FROM supervisor s
         INNER JOIN area a ON s.id_area = a.id_area
WHERE a.name = p_area
    LIMIT offset, p_size;
END //

DELIMITER ;
