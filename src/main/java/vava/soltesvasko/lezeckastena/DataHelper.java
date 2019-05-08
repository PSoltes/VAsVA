package vava.soltesvasko.lezeckastena;

import com.fasterxml.jackson.annotation.JsonProperty;
import vava.soltesvasko.lezeckastena.Data.Climber;

public class DataHelper {
    @JsonProperty("data")
    private Climber climber;

    public DataHelper(){};
    public DataHelper(Climber cl)
    {
        this.climber = cl;
    }

    public void setClimber(Climber cl)
    {
        this.climber = cl;
    }

    public Climber getClimber()
    {
        return this.climber;
    }
}
