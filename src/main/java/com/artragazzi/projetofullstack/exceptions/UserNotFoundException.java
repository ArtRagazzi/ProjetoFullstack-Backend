package com.artragazzi.projetofullstack.exceptions;

import com.artragazzi.projetofullstack.dto.UserDTO;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id)
    {
        super("User ID: "+id+" Not Found.");
    }
}
