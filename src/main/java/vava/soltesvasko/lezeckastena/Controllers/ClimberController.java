package vava.soltesvasko.lezeckastena.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vava.soltesvasko.lezeckastena.Data.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class ClimberController {

    @Autowired
    ClimberRepository climberRepo;

    @Autowired
    ClimberProblemRepository climberProblemsRepo;

    @Autowired
    ProblemRepository problemRepo;

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

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/climbers", produces = "application/json")
    public List<Climber> getClimbers() {
        var returnClimbers = climberRepo.findAll();

        if (returnClimbers.size() > 0)
            return returnClimbers;
        return null;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping(value="/add/{climberId}/{problemId}")
    public boolean addProblem(@PathVariable long climberId, @PathVariable long problemId)
    {
        Optional<Climber> c = climberRepo.findById(climberId);
        c.ifPresent(climber -> {
            Optional<Problem> p = problemRepo.findById(problemId);
            p.ifPresent(problem -> {
                ClimberProblem cp = new ClimberProblem(climber, problem, 0, false);
                climber.addMyProblems(cp);
                climberRepo.save(climber);
            });
        });

        return true;
    }
}
