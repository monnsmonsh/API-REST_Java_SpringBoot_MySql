package com.api.crud.services;


import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;


    //Metodo para listar por medio de un arra
    public ArrayList<UserModel> getUsers(){
        return  (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id){
        return userRepository.findById(id);
    }

    public UserModel updateById(UserModel request, Long id){
        UserModel user = userRepository.findById(id).get();

        user.setFistName(request.getFistName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        return user;
    }

    public Boolean deleteUser (Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }



}
