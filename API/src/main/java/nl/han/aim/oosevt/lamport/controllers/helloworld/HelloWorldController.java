package nl.han.aim.oosevt.lamport.controllers.helloworld;

import nl.han.aim.oosevt.lamport.controllers.helloworld.dto.HelloWorldResponseDTO;
import nl.han.aim.oosevt.lamport.services.helloworld.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {
    private final HelloWorldService helloWorldService;

    @Autowired
    public HelloWorldController(HelloWorldService helloWorldService) {
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
