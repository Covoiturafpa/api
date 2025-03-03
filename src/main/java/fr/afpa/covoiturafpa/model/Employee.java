package fr.afpa.covoiturafpa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;

@JsonTypeName("E")
@Entity
@DiscriminatorValue("E")
@Table(name = "employee", schema = "heroku_ext")
public class Employee extends Person {

    @JsonView(Views.SimpleUser.class)
    @Column(name = "is_admin")
    private boolean isAdmin = false;

    @JsonView(Views.SimpleUser.class)
    @Column(name = "is_teacher")
    private boolean isTeacher = false;

    @JsonView(Views.DetailedUser.class)
    @ManyToMany
    @JoinTable(name = "teacher_of", joinColumns = @JoinColumn(name = "id_teacher"), inverseJoinColumns = @JoinColumn(name = "id_formation"))
    public List<Formation> taughtFormations;


    public List<Formation> getTaughtFormations() {
        return taughtFormations;
    }

    public void setTaughtFormations(List<Formation> taughtFormations) {
        this.taughtFormations = taughtFormations;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }


    public Employee() {
    }
}
