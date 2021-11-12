package nl.han.aim.oosevt.lamport.data.helloworld;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldDAOImpl implements HelloWorldDAO {
    @Override
    public String getHelloWorld() {
        //Obviously this would come from a DB :shrug:
        return "Hello world!";
    }
}
