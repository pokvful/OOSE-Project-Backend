package nl.han.aim.oosevt.lamport.shared;

public enum Permissions {
    GET_AREAS("Bekijk gebieden"),
    UPDATE_AREAS("Wijzig gebieden"),
    CREATE_AREAS("Maak gebieden"),
    DELETE_AREAS("Verwijder gebieden"),
    GET_FRANCHISES("Bekijk franchises"),
    UPDATE_FRANCHISES("Wijzig franchises"),
    CREATE_FRANCHISES("Maak franchises"),
    DELETE_FRANCHISES("Verwijder franchises"),
    GET_GOALS("Bekijk doelstellingen"),
    UPDATE_GOALS("Wijzig doelstellingen"),
    CREATE_GOALS("Maak doelstellingen"),
    DELETE_GOALS("Verwijder doelstellingen"),
    GET_INTERVENTIONS("Bekijk interventies"),
    UPDATE_INTERVENTIONS("Wijzig interventies"),
    CREATE_INTERVENTIONS("Maak interventies"),
    DELETE_INTERVENTIONS("Verwijder interventies"),
    GET_LOCATIONS("Bekijk locaties"),
    UPDATE_LOCATIONS("Wijzig locaties"),
    CREATE_LOCATIONS("Maak locaties"),
    DELETE_LOCATIONS("Verwijder locaties"),
    GET_ROLES("Bekijk rollen"),
    UPDATE_ROLES("Wijzig rollen"),
    CREATE_ROLES("Maak rollen"),
    DELETE_ROLES("Verwijder rollen"),
    GET_USERS("Bekijk gebruikers"),
    UPDATE_USERS("Wijzig gebruikers"),
    CREATE_USERS("Maak gebruikers"),
    DELETE_USERS("Verwijder gebruikers");

    private final String display;

    Permissions(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
