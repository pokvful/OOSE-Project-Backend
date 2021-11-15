//package nl.han.aim.oosevt.lamport.services;
//
//import nl.han.aim.oosevt.lamport.data.helloworld.HelloWorldDAOImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//public class HelloWorldServiceTest {
//
//    private HelloWorldServiceImpl helloWorldService;
//
//    @BeforeEach
//    private void setup() {
//        final HelloWorldDAOImpl helloWorldDao = Mockito.mock(HelloWorldDAOImpl.class);
//        Mockito.when(helloWorldDao.getHelloWorld()).thenReturn("Hello world!");
//        helloWorldService = new HelloWorldServiceImpl(helloWorldDao);
//    }
//
//    @Test
//    public void helloWorldReturnsHelloWorld() {
//        Assertions.assertEquals("Hello world!", helloWorldService.getMessage());
//    }
//}
