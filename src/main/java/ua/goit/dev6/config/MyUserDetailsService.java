package ua.goit.dev6.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.goit.dev6.account.UserDAO;
import ua.goit.dev6.account.UserRepository;

@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO user = repository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with username %s not exists", username)));
        return new UserPrincipal(user);
    }
}
