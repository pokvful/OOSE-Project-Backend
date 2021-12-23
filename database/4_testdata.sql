use lbc;

CALL createRole("Gebruiker", "GET_AREAS,GET_FRANCHISES,GET_GOALS,GET_INTERVENTIONS,GET_LOCATIONS,GET_ROLES,GET_USERS");
CALL createRole("Beheerder", "GET_AREAS,UPDATE_AREAS,CREATE_AREAS,DELETE_AREAS,GET_FRANCHISES,UPDATE_FRANCHISES,CREATE_FRANCHISES,DELETE_FRANCHISES,GET_GOALS,UPDATE_GOALS,CREATE_GOALS,DELETE_GOALS,GET_INTERVENTIONS,UPDATE_INTERVENTIONS,CREATE_INTERVENTIONS,DELETE_INTERVENTIONS,GET_LOCATIONS,UPDATE_LOCATIONS,CREATE_LOCATIONS,DELETE_LOCATIONS,GET_ROLES,UPDATE_ROLES,CREATE_ROLES,DELETE_ROLES,GET_USERS,UPDATE_USERS,CREATE_USERS,DELETE_USERS");


CALL createGoal("Afvallen");
    CALL addProfileQuestionToGoal(1, "Wat is uw gewicht?");
    CALL addProfileQuestionToGoal(1, "Hoeveel wilt u afvallen?");

CALL createGoal("Geld Besparen");
    CALL addProfileQuestionToGoal(2, "Wat is uw maandinkomen?");
    CALL addProfileQuestionToGoal(2, "Hoeveel geeft u nu per maand uit?");
    CALL addProfileQuestionToGoal(2, "Hoeveel wilt u per maand extra overhouden?");

CALL createUser("Ivan", "ivan@hp2.nl", "$2a$10$hMGclFII4roSvokU7PQJeuWecWo1/DmXVloWzETgZjS1JzgzoyO7C", 2, 1);
CALL createUser("wur", "info@wur.nl", "$2a$10$hMGclFII4roSvokU7PQJeuWecWo1/DmXVloWzETgZjS1JzgzoyO7C", 2, 2);
CALL createUser("Bart", "bart@han.nl", "$2a$10$hMGclFII4roSvokU7PQJeuWecWo1/DmXVloWzETgZjS1JzgzoyO7C", 2, 1);
CALL createUser("Tim", "tim@han.nl", "$2a$10$hMGclFII4roSvokU7PQJeuWecWo1/DmXVloWzETgZjS1JzgzoyO7C", 2, 2);

CALL createCommand("Volkoren brood", "Welkom bij de Subway, kiest u voor een volkoren broodje?");
CALL createCommand("Salade", "Koop een salade");
CALL createCommand("Fruitteler", "Koop een zak appels");

CALL createQuestion("Kerk", "Hoe hoog is de kerktoren?");
    SET @question_id = LAST_INSERT_ID();
    CALL addAnswerToQuestion(@question_id, "100 meter");
    CALL addAnswerToQuestion(@question_id, "30 meter");
    CALL addAnswerToQuestion(@question_id, "55 meter");

CALL createQuestion("Bami", "Wat zit in een bamischijf?");
    SET @question_id = LAST_INSERT_ID();
    CALL addAnswerToQuestion(@question_id, "Rijst");
    CALL addAnswerToQuestion(@question_id, "Bami");
    CALL addAnswerToQuestion(@question_id, "Beide");

CALL createQuestion("Water", "Wat is water?");
    SET @question_id = LAST_INSERT_ID();
    CALL addAnswerToQuestion(@question_id, "letterlijk water");
    CALL addAnswerToQuestion(@question_id, "olie");
    CALL addAnswerToQuestion(@question_id, "De wetenschap is daar nog niet over uit");

CALL createQuestionnaire("Wiskunde quiz");
    SET @questionnaire_id = LAST_INSERT_ID();
    CALL addQuestionToQuestionnaire(@questionnaire_id, "5 x 5");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "12");
        CALL addAnswerToQuestion(@question_id, "25");
        CALL addAnswerToQuestion(@question_id, "14");
    CALL addQuestionToQuestionnaire(@questionnaire_id, "100 / 40");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "13");
        CALL addAnswerToQuestion(@question_id, "14 1/2");
        CALL addAnswerToQuestion(@question_id, "2 1/2");
    CALL addQuestionToQuestionnaire(@questionnaire_id, "Is 313 een priemgetal");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "Ja");
        CALL addAnswerToQuestion(@question_id, "Nee");

CALL createQuestionnaire("Konijnen");
    SET @questionnaire_id = LAST_INSERT_ID();
    CALL addQuestionToQuestionnaire(@questionnaire_id, "Wat is het belangrijkste voedsel voor een konijn?");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "Hooi");
        CALL addAnswerToQuestion(@question_id, "Voer");
        CALL addAnswerToQuestion(@question_id, "Blaadjes");
    CALL addQuestionToQuestionnaire(@questionnaire_id, "Wie denkt de baas te zijn in de relatie konijn-eigenaar?");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "Het konijn");
        CALL addAnswerToQuestion(@question_id, "De eigenaar");
        CALL addAnswerToQuestion(@question_id, "Beide");
    CALL addQuestionToQuestionnaire(@questionnaire_id, "Welk konijn heeft een eigen strip in de Donald Duck?");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "Lodewyck");
        CALL addAnswerToQuestion(@question_id, "Sammy");
        CALL addAnswerToQuestion(@question_id, "Broer konijn");

CALL createArea("Nijmegen", 5.85278, 51.8425, 600);
CALL createArea("Wageningen", 5.6653948, 51.9691868, 2350);
CALL createArea("Utrecht", 5.104480, 52.092876, 3500);

CALL createFranchise("McDonalds");
CALL createFranchise("Gaia_building (office)");
CALL createFranchise("Snumbwae");
CALL createFranchise("HP2 Consulting");

CALL createLocation("Mcdonalds Molenstraat", 5, 5.4323454, 51.987321321, 30, 1, 1, "2,4,5,7");
CALL createLocation("Gaia_building", 5, 5.666409706000024, 51.987491838000039, 100, 1, 2, "3,5,6");
CALL createLocation("Subway", 5, 5.664095127000053, 51.983383490000051, 100, 1, 3, "1,2,7,8");
CALL createLocation("Subway Utrecht", 5, 5.1088383, 52.0672984, 100, 3, 3, "1,2,7,8");
CALL createLocation("HP2 Kantoor", 10, 5.1100019, 52.0709499, 100, 3, 4, "4,5,6");
CALL createLocation("Danny's autopaleis", 10, 21.3221, 3.321, 100, 3, 4, "");

