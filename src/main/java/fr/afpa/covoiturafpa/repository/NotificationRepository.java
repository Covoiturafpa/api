package fr.afpa.covoiturafpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.Person;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    
    @Query("UPDATE Notification notif SET notif.isUnread = FALSE WHERE notif.isUnread = TRUE AND notif.person.id = :id")
    public List<Notification> updateAllUnreadByPerson(@Param("id") Integer id);

    @Query("DELETE Notification notif WHERE notif.person.id = :id")
    public void deleteAllByPerson(@Param("id") Integer id);

    public List<Optional<Notification>> findByPerson(Person person);

}
