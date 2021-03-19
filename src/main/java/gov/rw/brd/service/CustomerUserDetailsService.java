package gov.rw.brd.service;


import gov.rw.brd.domain.User;
import gov.rw.brd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

/**
 * Created by Nick on 1/16/2020.
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByUsername(username);
        if(user ==null) new UsernameNotFoundException("User not Found");
        return user;
    }
    @Transactional
    public User loadUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user ==null) new UsernameNotFoundException("User not Found");
            return user.get();
    }
}
