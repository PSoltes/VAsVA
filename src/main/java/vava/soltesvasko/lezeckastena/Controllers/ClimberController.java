package vava.soltesvasko.lezeckastena.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vava.soltesvasko.lezeckastena.Data.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class ClimberController {

    final Logger logger = LoggerFactory.getLogger(ClimberController.class);

    @Autowired
    ClimberRepository climberRepo;

    @Autowired
    ClimberProblemRepository climberProblemsRepo;

    @Autowired
    ProblemRepository problemRepo;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/climber/{id}", produces = "application/json")
    public Climber getClimber(@PathVariable long id) {
        logger.info("Request for a climber received.");
        logger.debug(String.format("GET request for climber with id (%d) received.", id));

        Optional<Climber> returnClimber = climberRepo.findById(id);
        if (returnClimber.isPresent()) {
            logger.info("Climber found.");
            return returnClimber.get();
        } else {
            logger.info("Climber not found.");
            return null;
        }
    }

    @PutMapping(value="/climber/{id}")
    public boolean updateClimber(@RequestBody Climber updatedClimber, @PathVariable long id)
    {
        logger.info("Request for a climber update received.");
        logger.debug(String.format("PUT request for climber update with id (%d) received.", id));
        logger.debug(String.format("Request body contents: (%s)", updatedClimber.toString()));

        Optional<Climber> oldClimber = climberRepo.findById(id);

        if(oldClimber.isPresent())
        {
            logger.info("Climber found.");

            oldClimber.map(climber -> {
                climber = updatedClimber;
                return climberRepo.save(climber);
            });

            logger.info("Climber successfully updated.");

            return true;
        }
        else
        {
            logger.info("Climber not found.");
            return false;
        }

    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/climbers", produces = "application/json")
    public List<Climber> getClimbers() {
        logger.info("Request for all the climbers received.");

        var returnClimbers = climberRepo.findAll();

        if (returnClimbers.size() > 0) {
            logger.info("Climber retrieval successful.");
            logger.debug(String.format("Total number of climbers found (%d).", returnClimbers.size()));

            return returnClimbers;
        }

        logger.info("No climbers found.");
        return null;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping(value="/add/{climberId}/{problemId}")
    public boolean addProblem(@PathVariable long climberId, @PathVariable long problemId)
    {
        logger.info("Request for adding a problem for a climber received.");
        logger.debug(String.format("Request to add problem with id (%d) for climber with id (%d).", problemId, climberId));

        Optional<Climber> c = climberRepo.findById(climberId);
        c.ifPresent(climber -> {
            logger.debug(String.format("Climber with id (%d) found.", climberId));
            Optional<Problem> p = problemRepo.findById(problemId);
            p.ifPresent(problem -> {
                logger.debug(String.format("Problem with id (%d) found.", problemId));
                logger.debug("Creating and adding new relationship.");

                ClimberProblem cp = new ClimberProblem(climber, problem, 0, false);
                climber.addMyProblems(cp);
                climberRepo.save(climber);

                logger.info("Problem-Climber relationship added.");
            });
        });

        return true;
    }
}
