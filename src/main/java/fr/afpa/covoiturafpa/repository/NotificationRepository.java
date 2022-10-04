package fr.afpa.covoiturafpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.Person;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    
    @Transactional
    @Modifying
    @Query("UPDATE Notification notif SET notif.isUnread = false WHERE notif.isUnread = true AND notif.person.id = :id")
    public int updateAllUnreadByPerson(@Param("id") Integer id);

    @Query("DELETE Notification notif WHERE notif.person.id = :id")
    public void deleteAllByPerson(@Param("id") Integer id);

    public List<Optional<Notification>> findByPerson(Person person);

    @Query("SELECT COUNT(notif) FROM Notification notif WHERE notif.isUnread = true AND notif.person.id = :id")
    public int countNewNotifications(@Param ("id") int id);
}
