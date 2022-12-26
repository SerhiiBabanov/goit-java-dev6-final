package ua.goit.dev6.signin;

import ua.goit.dev6.account.UserDAO;

import java.util.List;

public interface UsersService {

    UserDAO saveUser(UserDAO usersDto);


    List<UserDAO> findByName(String name);

}