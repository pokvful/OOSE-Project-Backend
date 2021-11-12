package nl.han.aim.oosevt.lamport.services.helloworld;

import nl.han.aim.oosevt.lamport.data.helloworld.HelloWorldDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldServiceImpl implements HelloWorldService {
    private final HelloWorldDAO helloWorldDao;

    @Autowired
    public HelloWorldServiceImpl(HelloWorldDAO helloWorldDao) {
        this.helloWorldDao = helloWorldDao;
    }

    @Override
    public String getMessage() {
        return helloWorldDao.getHelloWorld();
    }
}
