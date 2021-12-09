package nl.han.aim.oosevt.lamport.shared;

public interface HashProvider {
    String hash(String input);
    boolean matches(String input, String hash);
}
