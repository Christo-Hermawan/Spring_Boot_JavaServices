package demo.tesSwagger.controller;


import java.util.Collections;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.tesSwagger.model.crudObj;
import demo.tesSwagger.repository.crudRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping("/tes")
public class crudController {
    
    @Autowired
    private crudRepository loginRepo;

    @PostMapping("/save")
    public String saveUser(@RequestBody crudObj user) {
        loginRepo.save(user);

        return "Success";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody crudObj user, HttpServletRequest request) {
        crudObj existingUser = loginRepo.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", existingUser.getUsername());


            return ResponseEntity.ok().body(Collections.singletonMap("username", existingUser.getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<crudObj> update(@PathVariable("id") int id, @RequestBody crudObj user) {
    Optional<crudObj> userData = loginRepo.findById(id);

    if (userData.isPresent()) {
        crudObj data = userData.get();
        data.setUsername(user.getUsername());
        data.setPassword(user.getPassword());
        return new ResponseEntity<>(loginRepo.save(data), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

    @Transactional
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
        loginRepo.deleteByUsername(username);
        return ResponseEntity.noContent().build();
        
    }

    }
