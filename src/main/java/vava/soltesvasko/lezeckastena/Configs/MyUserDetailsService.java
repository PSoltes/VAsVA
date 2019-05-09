package vava.soltesvasko.lezeckastena.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vava.soltesvasko.lezeckastena.Data.Climber;
import vava.soltesvasko.lezeckastena.Data.ClimberRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ClimberRepository cRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Climber> climber = cRepo.findOneByEmail(email);
        if (climber.isPresent()){
            return new ClimberDetails(climber.get());
        }
        return null;
    }
}
