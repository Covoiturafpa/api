package fr.afpa.covoiturafpa.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@DiscriminatorValue("E")
@Table(name = "employee")
public class Employee extends Person {

    @Column
    private String role;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "is_teacher")
    private boolean isTeacher;

    @Column(name = "start_contract")
    private LocalDate startContract;

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
        return isAdmin;
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
