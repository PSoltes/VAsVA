package vava.soltesvasko.lezeckastena.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vava.soltesvasko.lezeckastena.Data.Climber;

@Repository
public interface ClimberProblemRepository extends JpaRepository<Climber, Long> {
}
