package vava.soltesvasko.lezeckastena.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vava.soltesvasko.lezeckastena.Data.*;
import vava.soltesvasko.lezeckastena.DataHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ClimberController {

    @Autowired
    ClimberProblemRepository CPRepo;
    @Autowired
    ClimberRepository climberRepo;
    @PersistenceContext
    EntityManager entityManager;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/climber/{id}", produces = "application/json")
    public Climber getClimber(@PathVariable long id) {
        Optional<Climber> returnClimber = climberRepo.findById(id);
        if (returnClimber.isPresent()) {
            return returnClimber.get();
        } else {
            return null;
        }
    }
    //@PreAuthorize("#id == principal.id || hasAuthority('ADMIN')")
    @PutMapping(value="/climber/{id}")
    public boolean updateClimber(@RequestBody Climber updatedClimber, @PathVariable long id)
    {
        Optional<Climber> oldClimber = climberRepo.findById(id);

        if(oldClimber.isPresent())
        {
            oldClimber.map(
              climber -> {climber = updatedClimber;
              return climberRepo.save(climber);}
            );
            return true;
        }
        else
        {
            return false;
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestParam("name") String email, @RequestParam("password") String password, @RequestParam("sex") char sex)
    {
        Optional<Climber> checkClimber = climberRepo.findOneByEmail(email);

        if(!checkClimber.isPresent()) {
            Climber cl = new Climber("", email, "{noop}" + password, "", 0, sex, "", "", "", "");
            climberRepo.save(cl);
            return ResponseEntity.status(HttpStatus.OK).body("Registrácia úspešná");        }
        else
        {
            return ResponseEntity.status(403).body("Používateľ už existuje");
        }
    }

    @PreAuthorize("#id == principal.id")
    @GetMapping(value = "climber/{id}/permissions")
    public ResponseEntity<List<String>> permissions(@PathVariable long id)
    {
        Optional<Climber> cl = climberRepo.findById(id);
        if(cl.isPresent())
        {
            List<Role> roles = cl.get().getRoles();
            List<String> result = new ArrayList<>();

            for(Role role : roles) {
                result.add(role.getName());
            }

            return ResponseEntity.status(200).body(result);
        }
        else
        {
            return ResponseEntity.status(500).body(new ArrayList<>());
        }
    }

}
