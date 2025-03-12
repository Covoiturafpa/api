package fr.afpa.covoiturafpa.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;

import fr.afpa.covoiturafpa.model.utils.Views;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@JsonTypeName("E")
@Entity
@DiscriminatorValue("E")
@Table(name = "employee", schema = "covoiturafpa")
public class Employee extends Person {

    @JsonView(Views.SimpleUser.class)
    @Column(name = "is_admin")
    private boolean isAdmin = false;

    @JsonView(Views.SimpleUser.class)
    @Column(name = "is_teacher")
    private boolean isTeacher = false;

    @JsonView(Views.SimpleUser.class)
    @Column(name = "id_centre")
    public int idCentre = 1;

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

    /**
     * Réimplémentation de "getAuthorities" pour ajouter les rôles "TEACHER" et/ou "ADMIN"
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) super.getAuthorities().stream().collect(Collectors.toList());

        if (this.isTeacher) {
            authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
        }

        if (this.isAdmin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        }

        return authorities;
    }
}
