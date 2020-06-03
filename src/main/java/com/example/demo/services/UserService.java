package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Lavet af Joakim
@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void createUserFromEmployee(Employee e) {
        User user = new User();
        user.setUsername(e.getFirst_name());
        user.setPassword(e.getLast_name());

        String jobtitle = e.getJob_title();
        setAuthorityFromJob(jobtitle, user);

        userRepo.createUser(user);
    }

    public void updateUserRoleByUsername(String jobtitle, String username) {
        User user = new User();
        setAuthorityFromJob(jobtitle, user);
        userRepo.updateUserRoleByUsername(user.getRole(), username);
    }

    public void setAuthorityFromJob(String jobtitle, User user) {
        switch (jobtitle) {
            case "Ejer":
                user.setRole("ROLE_ADMIN");
                break;
            case "Salgs Leder":
                user.setRole("ROLE_LEAD");
                break;
            case "Salgs Assistent":
                user.setRole("ROLE_SALES");
                break;
            case "Bogfører":
                user.setRole("ROLE_BOOKKEEPER");
                break;
            case "Rengøringspersonale":
                user.setRole("ROLE_CLEANING");
                break;
            case "Mekaniker":
                user.setRole("ROLE_MECHANIC");
                break;
        }
    }
}
