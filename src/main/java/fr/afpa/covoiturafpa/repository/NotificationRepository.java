package fr.afpa.covoiturafpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.covoiturafpa.model.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    
}
