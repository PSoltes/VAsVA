package vava.soltesvasko.lezeckastena.Data;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Entita reprezentujúca rolu, napr. USER, ADMIN etc.
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    private static final Logger logger = LoggerFactory.getLogger(Role.class);


    public String getName()
    {
        logger.trace(String.format("Getting role name with id (%d)", this.id));
        return this.name;
    }
}
