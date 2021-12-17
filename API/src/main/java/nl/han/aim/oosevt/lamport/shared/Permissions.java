package nl.han.aim.oosevt.lamport.shared;

public enum Permissions {
    GET_AREAS("Bekijk gebieden"),
    DELETE_AREAS("Verwijder gebieden");

    private final String display;

    Permissions(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
