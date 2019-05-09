package vava.soltesvasko.lezeckastena.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vava.soltesvasko.lezeckastena.Data.Climber;

import java.util.Optional;

@Repository
public interface ClimberRepository extends JpaRepository<Climber, Long> {

    public Optional<Climber> findOneByEmail(String email);
}
