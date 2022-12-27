package ua.goit.dev6.signin;

import jakarta.servlet.http.HttpServletRequest;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin( String username, String password, HttpServletRequest request);
}