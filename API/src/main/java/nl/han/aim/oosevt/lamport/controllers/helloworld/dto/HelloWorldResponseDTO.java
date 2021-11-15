package nl.han.aim.oosevt.lamport.controllers.helloworld.dto;

public class HelloWorldResponseDTO {
    private String message;

    public HelloWorldResponseDTO(String message){
        this.message = message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
