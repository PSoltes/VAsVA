package vava.soltesvasko.lezeckastena.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vava.soltesvasko.lezeckastena.Data.Climber;

@Repository
public interface ClimberProblemRepository extends JpaRepository<ClimberProblem, ClimberProblemKey> {
    public ClimberProblem findClimberProblemById(ClimberProblemKey cp);
}
