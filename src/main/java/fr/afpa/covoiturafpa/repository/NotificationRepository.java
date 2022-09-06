package fr.afpa.covoiturafpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Notification;
import fr.afpa.covoiturafpa.model.User;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    
    @Query("UPDATE Notification noti SET noti.isUnread = FALSE WHERE noti.isUnread = TRUE AND noti.user.id = :user.id")
    public List<Notification> updateAllUnreadByUser(User user);

    @Query("DELETE Notification noti WHERE noti.idUser = :user.id")
    public void deleteAllByUser(User user);

}
