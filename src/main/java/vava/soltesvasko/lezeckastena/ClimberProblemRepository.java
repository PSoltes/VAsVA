package vava.soltesvasko.lezeckastena;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimberProblemRepository extends JpaRepository<Climber, Long> {
}
