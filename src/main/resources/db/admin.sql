DELIMITER //

CREATE PROCEDURE sp_admin_search_by_name( IN p_name VARCHAR(50), IN p_page INT, IN p_size INT)
BEGIN
    DECLARE offset INT;
    SET offset = (p_page - 1) * p_size;

SELECT a.*
FROM admin a
INNER JOIN user u ON a.id_user = u.id_user
WHERE u.name LIKE CONCAT('%', p_name, '%')
    LIMIT offset, p_size;

END //

DELIMITER ;
