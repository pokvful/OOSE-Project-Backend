package nl.han.aim.oosevt.lamport.controllers.helloworld.dto;

public class HelloWorldResponseDto {
    private String message;

    public HelloWorldResponseDto(String message){
        this.message = message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
