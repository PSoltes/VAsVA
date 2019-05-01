package vava.soltesvasko.lezeckastena.Data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public String getName()
    {
        return this.name;
    }
}
