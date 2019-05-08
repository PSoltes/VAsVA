package vava.soltesvasko.lezeckastena.Data;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ClimberProblem {

    @EmbeddedId
    ClimberProblemKey id;

    //@JsonBackReference
    @ManyToOne
    @MapsId("climber_id")
    @JoinColumn(name="climber_id")
    Climber climber;

    //@JsonManagedReference
    @ManyToOne
    @MapsId("problem_id")
    @JoinColumn(name="problem_id")
    Problem problem;
    @CreatedDate
    private Date startedAt;

    private long attempts;
    private boolean finished;

    private static final Logger logger = LoggerFactory.getLogger(ClimberProblem.class);

    ClimberProblem(){}

    public ClimberProblem(Climber climber, Problem problem, long attempts, boolean finished)
    {
        this.attempts = attempts;
        this.finished = finished;
//        System.out.println(climber.toString());
//        System.out.println(problem.toString());
        this.id = new ClimberProblemKey(problem.getId(), climber.getId());

        logger.trace(String.format("Climber problem relation with ids (%d), (%d) created.", climber.getId(), problem.getId()));
    }
}
