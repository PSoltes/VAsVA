package vava.soltesvasko.lezeckastena;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    ProblemRepository repository;
    @Autowired
    ClimberRepository cRepo;


    @GetMapping(value = "/Insult", produces = "application/json")
    public List<Problem> index() {
        List<Pair<Climber,Problem>> result = new ArrayList<Pair<Climber,Problem>>();
        List<Climber> Palo = cRepo.findAll();
        List<Problem> problemList = repository.findAll();
        for(Problem p : problemList)
        {
            result.add(Pair.of(Palo.get(0),p));

        }
        return problemList;
    }

    @GetMapping(value = "/problems", produces = "application/json")
    public List<Problem> getProblems() {
        return repository.findAll();
    }

    @GetMapping(value = "/problem/{numericId:[\\d]+}", produces = "application/json")
    public Problem getProblem(@PathVariable long numericId) {
        return repository.findById(numericId).orElse(null);
    }

    @GetMapping(value = "/climbers", produces = "application/json")
    public List<Climber> getClimbers() {
        return cRepo.findAll();
    }

}