package vava.soltesvasko.lezeckastena.Data;

import lombok.Data;
import lombok.NonNull;
import org.codehaus.jackson.annotate.JsonCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class ClimberProblemKey implements Serializable {

    @Column(name="problem_id")
    @NonNull
    private long problemId;

    @Column(name="climber_id")
    @NonNull
    private long climberId;

    public ClimberProblemKey(){}

    private static final Logger logger = LoggerFactory.getLogger(ClimberProblemKey.class);

    public ClimberProblemKey(long problemId, long climberId)
    {
        this.problemId = problemId;
        this.climberId = climberId;

        logger.trace(String.format("Creating new ClimberProblemKey from ids (%d) and (%d).", problemId, climberId));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ClimberProblemKey that = (ClimberProblemKey) o;
        return Objects.equals(climberId, that.climberId) &&
                Objects.equals(problemId, that.problemId);
    }

    public Long getProblemId()
    {
        return this.problemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(problemId, climberId);
    }
}
