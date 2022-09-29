package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.utils.Views;

@JsonTypeName("E")
@Entity
@DiscriminatorValue("E")
@Table(name = "employee")
public class Employee extends Person {

    @JsonView(Views.SimpleUser.class)
    @Column
    private String role;

    @JsonView(Views.SimpleUser.class)
    @Column(name = "is_admin")
    private boolean isAdmin;

    @JsonView(Views.SimpleUser.class)
    @Column(name = "is_teacher")
    private boolean isTeacher;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "start_contract")
    private LocalDate startContract;

    @JsonView(Views.DetailedUser.class)
    @Column(name = "end_contract")
    private LocalDate endContract;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public LocalDate getStartContract() {
        return startContract;
    }

    public void setStartContract(LocalDate startContract) {
        this.startContract = startContract;
    }

    public LocalDate getEndContract() {
        return endContract;
    }

    public void setEndContract(LocalDate endContract) {
        this.endContract = endContract;
    }

    public Employee() {
    }
}
