package hr.diskobolos.config.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hr.diskobolos.model.security.User;
import hr.diskobolos.service.IUserService;
import java.util.Date;

/**
 * Service that handles basic authorization for the user
 *
 * @author Tomislav Čavka
 */
@Service
public class AuthenticationInfoRepository implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public AuthenticatedUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // Get User from Repository
        User user = userService.findByUsername(username);

        if (user != null) {
            // Convert User to Spring Security compatible format
            return AuthenticatedUser.from(user);
        } else {
            // User not found, throw exception
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}
