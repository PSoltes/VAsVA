package vava.soltesvasko.lezeckastena.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vava.soltesvasko.lezeckastena.Data.Problem;
import vava.soltesvasko.lezeckastena.Data.ProblemRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ProblemController {

    final Logger logger = LoggerFactory.getLogger(ProblemController.class);


    @Autowired
    ProblemRepository problemRepo;
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/problem/{id}", produces = "application/json")
    public Problem getProblem(@PathVariable long id) {
        logger.info("Requesting a problem.");
        logger.debug(String.format("Requesting a problem with id (%d).", id));

        Optional<Problem> returnProblem = problemRepo.findById(id);
        if (returnProblem.isPresent()) {
            logger.info("Problem found.");
            logger.debug(String.format("Problem with id (%d) has been found.", id));

            return returnProblem.get();
        } else {
            logger.info("Problem not found.");
            logger.debug(String.format("Problem with id (%d) has not been found.", id));
            return null;
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/problems", produces = "application/json")
    public List<Problem> getProblems() {
        logger.info("Requesting all problems.");

        var returnProblem = problemRepo.findAll();

        if (returnProblem.size() > 0) {
            logger.debug(String.format("Total problems found (%d)", returnProblem.size()));
            logger.info("Problems found.");
            return returnProblem;
        }

        logger.info("No problems found.");
        return null;
    }
}
