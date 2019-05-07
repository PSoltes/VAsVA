package vava.soltesvasko.lezeckastena.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vava.soltesvasko.lezeckastena.Data.Problem;
import vava.soltesvasko.lezeckastena.Data.ProblemRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ProblemController {

    @Autowired
    ProblemRepository problemRepo;
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/problem/{id}", produces = "application/json")
    public Problem getProblem(@PathVariable long id) {
        Optional<Problem> returnProblem = problemRepo.findById(id);
        if (returnProblem.isPresent()) {
            return returnProblem.get();
        } else {
            return null;
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/problems", produces = "application/json")
    public List<Problem> getProblems() {
        var returnProblem = problemRepo.findAll();

        if (returnProblem.size() > 0)
            return returnProblem;
        return null;
    }
}
