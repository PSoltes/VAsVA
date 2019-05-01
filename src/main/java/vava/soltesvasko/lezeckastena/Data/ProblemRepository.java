package vava.soltesvasko.lezeckastena.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vava.soltesvasko.lezeckastena.Data.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
