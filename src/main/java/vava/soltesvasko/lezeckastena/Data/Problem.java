package vava.soltesvasko.lezeckastena.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.List;

// Entita reprezentujúca Problém, má jeden vzťah many-to-one(jeden vie vytvoriť viac), a jeden
// one-to-many a problem môže mať viacero lezcov nastavené ako myProblem
@Entity
@Data
public class Problem {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    private String name ;
    private String grade;
    private String sector;
    private double maximumOverhangDegree;
    @ManyToOne
    @JoinColumn(name="setter_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"setProblems", "myProblems"})
    private Climber setter;
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    @JsonIgnoreProperties("problem")
    private List<ClimberProblem> myClimbers;
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdAt;
    private String type;
    private String picturePath;

    private static final Logger logger = LoggerFactory.getLogger(Problem.class);

    public Problem(){}

    public Problem(String name, String grade, String sector, double maximumOverhangDegree, String type, String picturePath, Climber setter)
    {
        super();
        this.name = name;
        this.grade = grade;
        this.sector = sector;
        this.maximumOverhangDegree = maximumOverhangDegree;
        this.type = type;
        this.picturePath = picturePath;
        this.setter = setter;
        logger.trace("Creating new Problem.");
    }

    public Long getId() {
        logger.trace(String.format("Getting id (%d).", this.id));
        return id;
    }

    public List<ClimberProblem> getMyClimbers() {
        return myClimbers;
    }

    public void setMyClimbers(List<ClimberProblem> myClimbers)
    {
        this.myClimbers = myClimbers;
    }

}
