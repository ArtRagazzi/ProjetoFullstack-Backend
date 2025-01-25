package com.artragazzi.projetofullstack.controller;

import com.artragazzi.projetofullstack.dto.UserDTO;
import com.artragazzi.projetofullstack.entity.User;
import com.artragazzi.projetofullstack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("v1/users")
public class UserController {



    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> find(@RequestParam(required = false) String login) {

        if (login != null) {
            return ResponseEntity.ok(userService
                    .findAll()
                    .stream()
                    .filter(myUser -> myUser.getLogin().equalsIgnoreCase(login))
                    .toList());
        }
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.insert(userDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
        //return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO  userDTO){
        UserDTO newUser = userService.update(id,userDTO);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
