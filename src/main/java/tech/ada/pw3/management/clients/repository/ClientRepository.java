package tech.ada.pw3.management.clients.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import tech.ada.pw3.management.clients.model.Client;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
    Flux<Client> findAll(Pageable pageable);
}
