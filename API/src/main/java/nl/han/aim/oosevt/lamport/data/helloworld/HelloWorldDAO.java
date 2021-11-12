package nl.han.aim.oosevt.lamport.data.helloworld;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldDAO implements IHelloWorldDAO {
    @Override
    public String getHelloWorld() {
        //Obviously this would come from a DB :shrug:
        return "Hello world!";
    }
}
