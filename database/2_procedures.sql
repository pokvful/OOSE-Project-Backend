USE lbc

DELIMITER //
CREATE PROCEDURE getAreas()
BEGIN
	SELECT a.area_name, a.area_id, g.longitude, g.latitude, g.radius FROM area a JOIN geofence g ON a.geofence_id = g.geofence_id;
END //

CREATE PROCEDURE getArea(IN param_id INT)
BEGIN
	SELECT a.area_name, a.area_id, g.longitude, g.latitude, g.radius FROM area a JOIN geofence g ON a.geofence_id = g.geofence_id WHERE a.area_id = param_id;
END //

CREATE PROCEDURE createArea(
    IN param_name VARCHAR(255),
    IN param_longitude DECIMAL(9,6),
    IN param_latitude DECIMAL(9,6),
    IN param_radius INT)
BEGIN
    DECLARE var_geofence_id INT DEFAULT 0;

    INSERT INTO geofence(longitude, latitude, radius) VALUES (param_longitude, param_latitude, param_radius);

    SET var_geofence_id = LAST_INSERT_ID();
	
    INSERT INTO area (geofence_id, area_name) VALUES (var_geofence_id, param_name);
END //

CREATE PROCEDURE updateArea(
    IN param_id INT,
    IN param_name VARCHAR(255),
    IN param_longitude DECIMAL(9,6),
    IN param_latitude DECIMAL(9,6),
    IN param_radius INT)
BEGIN
    UPDATE geofence SET longitude = param_longitude, latitude = param_latitude, radius = param_radius WHERE geofence_id = (SELECT geofence_id FROM area WHERE area_id = param_id);
    UPDATE area SET area_name = param_name WHERE area_id = param_id;
END //

CREATE PROCEDURE deleteArea(
    IN param_id INT)
BEGIN
    DELETE FROM location WHERE area_id = param_id;
    DELETE FROM area WHERE area_id = param_id;
	DELETE FROM geofence WHERE geofence_id = (SELECT geofence_id FROM area WHERE area_id = param_id);
END //

DELIMITER ;