package com.artragazzi.projetofullstack.service;

import com.artragazzi.projetofullstack.dto.UserDTO;
import com.artragazzi.projetofullstack.entity.User;
import com.artragazzi.projetofullstack.exceptions.UserNotFoundException;
import com.artragazzi.projetofullstack.mapper.UserMapper;
import com.artragazzi.projetofullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {


    private static final UserMapper MAPPER = UserMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(eachUser -> MAPPER.toDTO(eachUser)).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        //Ajustar para exception personalizada
        return MAPPER.toDTO(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Transactional
    public UserDTO insert(UserDTO userDTO) {
        User user = MAPPER.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return MAPPER.toDTO(savedUser);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        // Atualiza os campos da entidade com os valores do DTO
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return MAPPER.toDTO(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(user.getId());

    }
}
