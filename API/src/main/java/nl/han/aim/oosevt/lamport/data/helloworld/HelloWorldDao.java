package nl.han.aim.oosevt.lamport.data.helloworld;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldDao implements IHelloWorldDao {
    @Override
    public String getHelloWorld() {
        //Obviously this would come from a DB :shrug:
        return "Hello world!";
    }
}
