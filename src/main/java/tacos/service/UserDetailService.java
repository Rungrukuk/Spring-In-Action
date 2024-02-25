package tacos.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

/*
 * Spring Security offers several out-of-the-box implementations of
 * UserDetailsService, including the following:
 *  An in-memory user store
 *  A JDBC user store
 *  An LDAP user store
 */
