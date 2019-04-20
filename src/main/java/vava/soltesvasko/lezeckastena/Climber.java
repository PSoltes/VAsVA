package vava.soltesvasko.lezeckastena;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "setter")
    private Set<Problem> setProblems;
    @JsonManagedReference
    @OneToMany(mappedBy = "climber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClimberProblem> myProblems;

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

    public Long getId() {
        return this.id;
    }
}
