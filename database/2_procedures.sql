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
    IN var_name VARCHAR(255),
    IN var_delay INT,
    IN var_longitude DECIMAL(9,6),
    IN var_latitude DECIMAL(9, 6),
    IN var_radius INT,
    IN var_areaId INT
) BEGIN
    DECLARE var_geofence_id INT DEFAULT 0;

    INSERT INTO geofence (longitude, latitude, radius)
    VALUES (var_longitude, var_latitude, var_radius);

    SET var_geofence_id = LAST_INSERT_ID();

    INSERT INTO location (area_id, geofence_id, location_name, delay)
    VALUES (var_areaId, var_geofence_id, var_name, var_delay);
END //

CREATE PROCEDURE getLocationById(
    IN id INT
) BEGIN
    SELECT location_id, location_name, delay, location_geofence.longitude, location_geofence.latitude, location_geofence.radius, area.area_id area_id, area.area_name area_name, area_geofence.latitude area_latitude, area_geofence.longitude area_longitude, area_geofence.radius area_radius
    FROM location
    LEFT OUTER JOIN geofence AS location_geofence ON location_geofence.geofence_id = location.geofence_id
    LEFT OUTER JOIN area ON location.area_id = area.area_id
    LEFT OUTER JOIN geofence AS area_geofence ON area_geofence.geofence_id = area.geofence_id
    WHERE location_id = id;
END //


CREATE PROCEDURE getLocations()
BEGIN
    SELECT location_id, location_name, delay, location_geofence.longitude, location_geofence.latitude, location_geofence.radius, area.area_id area_id, area.area_name area_name, area_geofence.latitude area_latitude, area_geofence.longitude area_longitude, area_geofence.radius area_radius
    FROM location
    LEFT OUTER JOIN geofence AS location_geofence ON location_geofence.geofence_id = location.geofence_id
    LEFT OUTER JOIN area ON location.area_id = area.area_id
    LEFT OUTER JOIN geofence AS area_geofence ON area_geofence.geofence_id = area.geofence_id;
END //

CREATE PROCEDURE updateLocation(
    in id INT,
    IN name VARCHAR(255),
    IN delay INT,
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
        WHERE location_id = id
    );

    UPDATE location
    SET area_id=areaId, location_name=name, delay = delay 
    WHERE location_id = id;
END //

CREATE PROCEDURE deleteLocation(
    IN param_id INT
) BEGIN
    DELETE FROM location WHERE location_id = param_id;
	DELETE FROM geofence WHERE geofence_id = (SELECT geofence_id FROM location WHERE location_id = param_id);
END //
DELIMITER ;