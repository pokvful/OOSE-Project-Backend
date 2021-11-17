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

CREATE PROCEDURE createLocation(
    IN name VARCHAR(255),
    IN sensitivity INT,
    IN longitude DECIMAL(9,6),
    IN latitude DECIMAL(9, 6),
    IN radius INT,
    IN areaId INT
) BEGIN
    INSERT INTO geofence (longitude, latitude, radius)
    VALUES (longitude, latitude, radius);

    INSERT INTO location (area_id, geofence_id, location_name, sensitivity)
    VALUES (areaId, LAST_INSERT_ID(), name, sensitivity);
END //

CREATE PROCEDURE getLocationById(
    IN id INT
) BEGIN
    SELECT location_id, area_id, location_name, delay, longitude, latitude, radius
    FROM location
    LEFT OUTER JOIN geofence ON geofence.geofence_id = location.location_id
    WHERE location_id = id;
END //


CREATE PROCEDURE getLocations(
    IN id INT
) BEGIN
    SELECT location_id, area_id, location_name, delay, longitude, latitude, radius
    FROM location
    LEFT OUTER JOIN geofence ON geofence.geofence_id = location.location_id;
END //

CREATE PROCEDURE updateLocation(
    in id INT,
    IN name VARCHAR(255),
    IN sensitivity INT,
    IN longitude DECIMAL(9,6),
    IN latitude DECIMAL(9, 6),
    IN radius INT,
    IN areaId INT
) BEGIN

    UPDATE geofence
    SET longitude=longitude, latitude=latitude, radius=radius
    WHERE geofence_id = (
        SELECT geofence_id
        FROM location
        WHERE location_id = 2
    );

    UPDATE location
    SET area_id=areaId, location_name=name, sensitivity = sensitivity;

END //

CREATE PROCEDURE deleteLocation(
    IN param_id INT
) BEGIN
    DELETE FROM location WHERE location_id = param_id;
	DELETE FROM geofence WHERE geofence_id = (SELECT geofence_id FROM location WHERE location_id = param_id);
END //
DELIMITER ;