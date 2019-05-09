package vava.soltesvasko.lezeckastena.Data;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

// Entita reprezentujúca Lezca
@Entity
@Data
public class Climber {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    private String name;
    private String email;
    private String password;
    private String bio;
    private int age;
    private char sex;
    private String contact;
    private String grade;
    private String nickname;
    private String status;
    @OneToMany(mappedBy = "setter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"setter", "myClimbers"})
    private List<Problem> setProblems;
    @OneToMany(mappedBy = "climber", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("climber")
    private List<ClimberProblem> myProblems;
    @ElementCollection
    @CollectionTable(name = "pictures", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "pictures")
    private List<String> myImages;
    @JoinTable(name = "climber_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private String profilePicPath;

    private static final Logger logger = LoggerFactory.getLogger(Climber.class);

    public Climber(){}

    public Climber(String name, String email, String password, String bio, int age, char sex, String contact, String grade, String nickname, String status)
    {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.grade = grade;
        this.nickname = nickname;
        this.status = status;

        logger.trace("Creating new Climber.");
    }

    public void setMyProblems(List<ClimberProblem> myProblems) {
        logger.trace(String.format("Setting problems list for climber (%d)", this.id));
        this.myProblems = myProblems;
    }
    public void setMyImages(List<String> myImages){this.myImages = myImages;}

    public List<String> getMyImages() {return this.myImages;}
    public String getProfilePicPath(){return this.profilePicPath;}
    public void addMyProblems(ClimberProblem cp) {
        logger.trace("Adding problems to climber problems.");
        this.myProblems.add(cp);
    }
    public Long getId() {
        logger.trace(String.format("Getting id (%d).", this.id));
        return this.id;
    }
    public String getPassword() {
        logger.trace(String.format("Getting password for climber (%d).", this.id));
        return this.password;
    }
    public String getEmail() {
        logger.trace(String.format("Getting email for climber (%d).", this.id));
        return this.email;
    }
    public List<Role> getRoles()
    {
        logger.trace(String.format("Getting roles for climber (%d).", this.id));
        return this.roles;
    }

    public List<ClimberProblem> getMyProblems()
    {
        return this.myProblems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
