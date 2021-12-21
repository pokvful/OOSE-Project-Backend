USE lbc;

DELIMITER //

CREATE TRIGGER commandCorrectType
BEFORE INSERT
ON command

    FOR EACH ROW
    BEGIN
        SET @intervention_type = (
            SELECT intervention_type
            FROM intervention
            WHERE intervention.intervention_id = NEW.intervention_id
        );

        IF @intervention_type <> 'command' THEN
            SET NEW.intervention_id = NULL;
        END IF;
    END;

DELIMITER ;