package vava.soltesvasko.lezeckastena;

import lombok.Data;
import lombok.NonNull;

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

    ClimberProblemKey(){}

    ClimberProblemKey(long problemId, long climberId)
    {
        this.problemId = problemId;
        this.climberId = climberId;
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

    @Override
    public int hashCode() {
        return Objects.hash(problemId, climberId);
    }
}
