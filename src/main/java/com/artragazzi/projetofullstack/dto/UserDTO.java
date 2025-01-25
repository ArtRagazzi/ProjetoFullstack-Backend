package com.artragazzi.projetofullstack.dto;


import com.artragazzi.projetofullstack.entity.User;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Long id;
    private String name;
    private String login;
    private String email;
    private String password;
}


