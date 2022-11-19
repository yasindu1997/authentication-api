package lk.slit.demo.controller;

import lk.slit.demo.entity.User;
import lk.slit.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepo userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User user) {
        if (!userRepository.existsById(user.getEmail())) {
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@RequestBody User user) {
        if (userRepository.existsById(user.getEmail())) {
            User userAuth = userRepository.findById(user.getEmail()).get();
            if(userAuth.getPassword().equals(user.getPassword())){
                return new ResponseEntity("Login Success", HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity("Login Failed", HttpStatus.FORBIDDEN);
    }

}
