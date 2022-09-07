package fr.afpa.covoiturafpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    
    @Query("UPDATE Notification noti SET noti.isUnread = FALSE WHERE noti.isUnread = TRUE AND noti.user.id = :id")
    public List<Notification> updateAllUnreadByUser(@Param("id") Integer id);

    @Query("DELETE Notification noti WHERE noti.user.id = :id")
    public void deleteAllByUser(@Param("id") Integer id);

}
