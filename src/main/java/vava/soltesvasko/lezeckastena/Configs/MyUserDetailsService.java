package vava.soltesvasko.lezeckastena.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vava.soltesvasko.lezeckastena.Data.Climber;
import vava.soltesvasko.lezeckastena.Data.ClimberRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ClimberRepository cRepo;

    @Override
    public UserDetails loadUserByUsername(String email)
    {
        Climber climber = cRepo.findOneByEmail(email);
        return new ClimberDetails(climber);
    }
}
