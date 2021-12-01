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
    DECLARE param_geofence_id INT DEFAULT 0;

    INSERT INTO geofence(longitude, latitude, radius) VALUES (param_longitude, param_latitude, param_radius);

    SET param_geofence_id = LAST_INSERT_ID();
	
    INSERT INTO area (geofence_id, area_name) VALUES (param_geofence_id, param_name);
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
    DELETE FROM location_intervention WHERE location_id IN (SELECT location_id FROM location WHERE area_id = param_id);
    DELETE FROM location WHERE area_id = param_id;
    DELETE FROM area WHERE area_id = param_id;
	DELETE FROM geofence WHERE geofence_id = (SELECT geofence_id FROM area WHERE area_id = param_id);
END //

CREATE PROCEDURE createLocation(
    IN param_name VARCHAR(255),
    IN param_delay INT,
    IN param_longitude DECIMAL(9,6),
    IN param_latitude DECIMAL(9, 6),
    IN param_radius INT,
    IN param_areaId INT,
    IN param_franchiseId INT,
    IN param_intervention_ids VARCHAR(500)) 
BEGIN
    DECLARE param_geofence_id INT DEFAULT 0;
    DECLARE last_insert_id INT DEFAULT 0;

    INSERT INTO geofence (longitude, latitude, radius)
    VALUES (param_longitude, param_latitude, param_radius);

    SET param_geofence_id = LAST_INSERT_ID();

    INSERT INTO location (area_id, geofence_id, location_name, delay, franchise_id)
    VALUES (param_areaId, param_geofence_id, param_name, param_delay, param_franchiseId);

    SET last_insert_id = LAST_INSERT_ID();

    IF param_intervention_ids != "" THEN
        CREATE TEMPORARY TABLE converted_values (id int(11));
        SET @sqlvar = CONCAT("INSERT INTO converted_values (id) VALUES ('", REPLACE(param_intervention_ids, ",", "'),('"),"');");
        PREPARE insert_statement FROM @sqlvar;
        EXECUTE insert_statement;

        INSERT INTO location_intervention (location_id, intervention_id) SELECT last_insert_id, id FROM converted_values;
        DROP TEMPORARY TABLE converted_values;
    END IF;
END //

CREATE PROCEDURE getLocationById(
    IN id INT
) BEGIN
    SELECT 
        location_id, 
        location_name, 
        delay, 
        location_geofence.longitude, 
        location_geofence.latitude, 
        location_geofence.radius, 
        area.area_id AS area_id, 
        area.area_name AS area_name, 
        franchise.franchise_id AS franchise_id, 
        franchise.franchise_name AS franchise_name, 
        area_geofence.latitude AS area_latitude, 
        area_geofence.longitude AS area_longitude, 
        area_geofence.radius AS area_radius, 
        (SELECT GROUP_CONCAT(intervention_id) FROM location_intervention WHERE location_intervention.location_id = location_id) AS linked_interventions
    FROM location
    LEFT OUTER JOIN geofence AS location_geofence ON location_geofence.geofence_id = location.geofence_id
    LEFT OUTER JOIN area ON location.area_id = area.area_id
    LEFT OUTER JOIN franchise ON location.franchise_id = franchise.franchise_id
    LEFT OUTER JOIN geofence AS area_geofence ON area_geofence.geofence_id = area.geofence_id
    WHERE location_id = id;
END //

CREATE PROCEDURE getInterventionsByLocationId(
    IN param_location_id INT
)
BEGIN
    SELECT intervention.intervention_id AS intervention_id, intervention.intervention_name AS intervention_name
    FROM location_intervention
    LEFT OUTER JOIN intervention ON location_intervention.intervention_id = intervention.intervention_id
    WHERE location_intervention.location_id = param_location_id;
END //

CREATE PROCEDURE getLocations()
BEGIN
    SELECT 
    location_id, 
    location_name, 
    delay, 
    location_geofence.longitude, 
    location_geofence.latitude, 
    location_geofence.radius, 
    area.area_id AS area_id, 
    area.area_name AS area_name, 
    franchise.franchise_id AS franchise_id, 
    franchise.franchise_name AS franchise_name, 
    area_geofence.latitude AS area_latitude, 
    area_geofence.longitude AS area_longitude, 
    area_geofence.radius AS area_radius, 
    (SELECT GROUP_CONCAT(intervention_id) FROM location_intervention WHERE location_intervention.location_id = location_id) AS linked_interventions
    FROM location
    LEFT OUTER JOIN geofence AS location_geofence ON location_geofence.geofence_id = location.geofence_id
    LEFT OUTER JOIN area ON location.area_id = area.area_id
        LEFT OUTER JOIN franchise ON location.franchise_id = franchise.franchise_id
    LEFT OUTER JOIN geofence AS area_geofence ON area_geofence.geofence_id = area.geofence_id;
END //

CREATE PROCEDURE updateLocation(
    in param_id INT,
    IN param_name VARCHAR(255),
    IN param_delay INT,
    IN param_longitude DECIMAL(9,6),
    IN param_latitude DECIMAL(9, 6),
    IN param_radius INT,
    IN param_areaId INT,
    IN param_franchiseId INT,
    IN param_intervention_ids VARCHAR(500)
) BEGIN

    UPDATE geofence
    SET longitude=param_longitude, latitude=param_latitude, radius=param_radius
    WHERE geofence_id = (
        SELECT geofence_id
        FROM location
        WHERE location_id = param_id
    );

    UPDATE location
    SET area_id=param_areaId, location_name=param_name, delay = param_delay, franchise_id = param_franchiseId
    WHERE location_id = param_id;

    DELETE FROM location_intervention WHERE location_id = param_id;

    IF param_intervention_ids != "" THEN
        CREATE TEMPORARY TABLE converted_values (id int(11));
        SET @sqlvar = CONCAT("INSERT INTO converted_values (id) VALUES ('", REPLACE(param_intervention_ids, ",", "'),('"),"');");
        PREPARE insert_statement FROM @sqlvar;
        EXECUTE insert_statement;

        INSERT INTO location_intervention (location_id, intervention_id) SELECT param_id, id FROM converted_values;
        DROP TEMPORARY TABLE converted_values;
    END IF;
END //

CREATE PROCEDURE deleteLocation(
    IN param_id INT
) BEGIN
    DELETE FROM location WHERE location_id = param_id;
	DELETE FROM geofence WHERE geofence_id = (SELECT geofence_id FROM location WHERE location_id = param_id);
END //

CREATE PROCEDURE getFranchiseById(
    IN id INT
) BEGIN
    SELECT franchise_id, franchise_name
    FROM franchise
    WHERE franchise_id = id;
END //

CREATE PROCEDURE getFranchises()
BEGIN
    SELECT franchise_id, franchise_name
    FROM franchise;
END //

CREATE PROCEDURE deleteFranchise(
    IN param_id INT
) BEGIN
    DELETE FROM location_intervention WHERE location_id IN (SELECT location_id FROM location WHERE franchise_id = param_id);
    DELETE FROM location WHERE franchise_id = param_id;
    DELETE FROM franchise_intervention WHERE franchise_id = param_id;
    DELETE FROM franchise WHERE franchise_id = param_id;
END //

CREATE PROCEDURE updateFranchise(
    IN param_id INT,
    IN param_name VARCHAR(255)
) BEGIN
    UPDATE franchise
        SET franchise_name = param_name
    WHERE franchise_id = param_id;
END //

CREATE PROCEDURE createFranchise(
    IN param_name VARCHAR(255)
) BEGIN
    INSERT INTO franchise(franchise_name) VALUES (param_name);
END //
DELIMITER ;
