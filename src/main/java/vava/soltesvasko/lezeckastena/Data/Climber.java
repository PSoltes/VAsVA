package vava.soltesvasko.lezeckastena.Data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @OneToMany(mappedBy = "setter")
    private Set<Problem> setProblems;
    @OneToMany(mappedBy = "climber", cascade = CascadeType.ALL, orphanRemoval = true)
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
    }

    public void setMyProblems(List<ClimberProblem> myProblems) {
        this.myProblems = myProblems;
    }
    public void setMyImages(List<String> myImages){this.myImages = myImages;}

    public List<String> getMyImages() {return this.myImages;}
    public String getProfilePicPath(){return this.profilePicPath;}
    public Long getId() {
        return this.id;
    }
    public String getPassword() {
        return this.password;
    }
    public String getEmail() {
        return this.email;
    }
    public List<Role> getRoles()
    {
        return this.roles;
    }

}
