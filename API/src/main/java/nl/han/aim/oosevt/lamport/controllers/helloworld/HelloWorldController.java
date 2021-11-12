package nl.han.aim.oosevt.lamport.controllers.helloworld;

import nl.han.aim.oosevt.lamport.controllers.helloworld.dto.HelloWorldResponseDTO;
import nl.han.aim.oosevt.lamport.services.helloworld.IHelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {
    private final IHelloWorldService helloWorldService;

    @Autowired
    public HelloWorldController(IHelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("")
    public ResponseEntity<HelloWorldResponseDTO> get () {
        return new ResponseEntity<>(
                new HelloWorldResponseDTO(helloWorldService.getMessage()),
                HttpStatus.OK
        );
    }

    @PutMapping("")
    public ResponseEntity<HelloWorldResponseDTO> put (String message) {
        return new ResponseEntity<>(
                new HelloWorldResponseDTO(message),
                HttpStatus.OK
        );
    }
}
