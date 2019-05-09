package vava.soltesvasko.lezeckastena.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

// Entita reprezentujúca väzobnú tabuľku pre problémy a ich lezcov. Tým pádom, že obsahuje aj
// iné dôležité informácie ine je vzťah obsahuje dve many-to-one vzťahy.
@Entity
@Data
public class ClimberProblem {

    @EmbeddedId
    ClimberProblemKey id;

    @ManyToOne
    @MapsId("climber_id")
    @JsonIgnoreProperties({"setProblems", "myProblems"})
    @JoinColumn(name="climber_id")
    Climber climber;

    @ManyToOne
    @MapsId("problem_id")
    @JoinColumn(name="problem_id")
    @JsonIgnoreProperties({"setter", "myClimbers"})
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

    public void setClimber(Climber climber)
    {
        this.climber = climber;
    }

    public Problem getProblem()
    {
        return this.problem;
    }

    public long getAttempts() {
        return attempts;
    }

    public boolean isFinished() {
        return finished;
    }

    public Long getId()
    {
        return this.id.getProblemId();
    }

    public void update(long attempts, boolean finished)
    {
        this.attempts = attempts;
        this.finished = finished;
    }
}
