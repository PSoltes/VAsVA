package vava.soltesvasko.lezeckastena.Configs;

import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vava.soltesvasko.lezeckastena.Data.Climber;
import vava.soltesvasko.lezeckastena.Data.Role;

import java.util.ArrayList;
import java.util.Collection;

public class ClimberDetails implements UserDetails {

    private Climber climber;
    public ClimberDetails(Climber climber) {
        this.climber = climber;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection roles = new ArrayList();
        Hibernate.initialize(climber.getRoles());
        for(Role role : climber.getRoles())
        {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }

        return roles;
    }
    public String getProfilePicPath(){return climber.getProfilePicPath();}
    public long getId(){return climber.getId();}
    public String getPassword()
    {
        return climber.getPassword();
    }
    public String getUsername()
    {
        return climber.getEmail();
    }
    public boolean isAccountNonExpired()
    {
        return true;
    }
    public boolean isAccountNonLocked()
    {
        return true;
    }
    public boolean isCredentialsNonExpired()
    {
        return true;
    }
    public boolean isEnabled()
    {
        return true;
    }
}
