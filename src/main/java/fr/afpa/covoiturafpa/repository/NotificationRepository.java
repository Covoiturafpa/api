package fr.afpa.covoiturafpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    
    @Query("UPDATE Notification notif SET noti.isUnread = FALSE WHERE notif.isUnread = TRUE AND notif.user.id = :id")
    public List<Notification> updateAllUnreadByUser(@Param("id") Integer id);

    @Query("DELETE Notification notif WHERE notif.user.id = :id")
    public void deleteAllByUser(@Param("id") Integer id);

}
