package vava.soltesvasko.lezeckastena.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import vava.soltesvasko.lezeckastena.Data.*;
import vava.soltesvasko.lezeckastena.DataHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.http.HttpResponse;
import java.util.ArrayList;


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
    //@PreAuthorize("#id == principal.id || hasAuthority('ADMIN')")
    @PutMapping(value="/climber/{id}")
    public boolean updateClimber(@RequestBody Climber updatedClimber, @PathVariable long id)
    {
        logger.info("Request for a climber update received.");
        logger.debug(String.format("PUT request for climber update with id (%d) received.", id));
        logger.debug(String.format("Request body contents: (%s)", updatedClimber.toString()));

        Optional<Climber> oldClimber = climberRepo.findById(id);
        Climber oClimber = null;
        if(oldClimber.isPresent()) {
            oClimber = oldClimber.get();
            oClimber.setName(updatedClimber.getName());
            oClimber.setBio(updatedClimber.getBio());
            oClimber.setAge(updatedClimber.getAge());
            oClimber.setSex(updatedClimber.getSex());
            oClimber.setBio(updatedClimber.getEmail());
            oClimber.setContact(updatedClimber.getContact());
            oClimber.setNickname(updatedClimber.getNickname());
            oClimber.setStatus(updatedClimber.getStatus());
            climberRepo.save(oClimber);
            return true;

        }
        logger.info("Climber not found.");

        return false;
    }

    @PutMapping(value = "/update/climber/{id}")
    public boolean updateProblemClimber(@RequestParam("attempts") long attempts, @RequestParam("finished") boolean finished, @RequestParam("problem_id") long problem_id, @PathVariable("id") long id)
    {
        ClimberProblem temp = climberProblemsRepo.findClimberProblemById(new ClimberProblemKey(problem_id, id));
        temp.update(attempts, finished);
        climberProblemsRepo.save(temp);
        return true;
    }

    @DeleteMapping(value = "/climber/{id}/problem/{problem_id}")
    public ResponseEntity<String> deleteProblem(@PathVariable("id") long id, @PathVariable("problem_id") long problem_id)
    {
        Optional<Climber> cl = climberRepo.findById(id);
        if(cl.isPresent()) {
            for (ClimberProblem CP : cl.get().getMyProblems()) {
                if (CP.getId() == problem_id) {
                    cl.get().getMyProblems().remove(CP);
                }
            }
            ClimberProblem toDelete = climberProblemsRepo.findClimberProblemById(new ClimberProblemKey(problem_id, id));
            climberProblemsRepo.delete(toDelete);

            return ResponseEntity.status(200).body("problem zmazany");
        }
        return ResponseEntity.status(404).body("pouzivatel nenajdeny");
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
//                problemRepo.save(problem);
                climberRepo.save(climber);
//                climberProblemsRepo.save(cp);

                logger.info("Problem-Climber relationship added.");
            });
        });

        return true;
    }
}
