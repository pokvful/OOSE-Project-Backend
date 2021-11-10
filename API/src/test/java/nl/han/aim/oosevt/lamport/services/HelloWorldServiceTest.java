package nl.han.aim.oosevt.lamport.services;

import nl.han.aim.oosevt.lamport.data.helloworld.HelloWorldDao;
import nl.han.aim.oosevt.lamport.services.helloworld.HelloWorldService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HelloWorldServiceTest {

    private HelloWorldService helloWorldService;

    @BeforeEach
    private void setup() {
        final HelloWorldDao helloWorldDao = Mockito.mock(HelloWorldDao.class);
        Mockito.when(helloWorldDao.getHelloWorld()).thenReturn("Hello world!");
        helloWorldService = new HelloWorldService(helloWorldDao);
    }

    @Test
    public void helloWorldReturnsHelloWorld() {
        Assertions.assertEquals("Hello world!", helloWorldService.getMessage());
    }
}
