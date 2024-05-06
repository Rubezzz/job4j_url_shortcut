package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.UrlUser;
import ru.job4j.urlshortcut.repository.UserRepository;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UrlUser user = users.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }
}
