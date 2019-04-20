package vava.soltesvasko.lezeckastena;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClimberController {

    @Autowired
    ClimberRepository climberRepo;

    @GetMapping(value = "/climber/{id}", produces = "application/json")
    public Climber getClimber(@PathVariable long id) {
        Optional<Climber> returnClimber = climberRepo.findById(id);
        if (returnClimber.isPresent()) {
            return returnClimber.get();
        } else {
            return null;
        }
    }
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
}
