package com.sudagoarth._Auth.repositories;

import com.sudagoarth._Auth.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // You can define custom query methods here if needed
    // Example: List<Event> findByCategory(String category);
}
