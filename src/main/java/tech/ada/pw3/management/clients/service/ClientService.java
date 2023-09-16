package tech.ada.pw3.management.clients.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.ada.pw3.management.clients.dto.ClientDTO;
import tech.ada.pw3.management.clients.dto.parser.ClientParser;
import tech.ada.pw3.management.clients.exception.DataNotFoundException;
import tech.ada.pw3.management.clients.model.Client;
import tech.ada.pw3.management.clients.repository.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Flux<Client> getClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Mono<Client> findById(String id) {
        return clientRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new DataNotFoundException(
                                String.format("Cliente não encontrado com id: %s", id))));
    }

    public Mono<Client> saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Mono<Client> update(ClientDTO clientUpdated, String id) {
        findById(id);

        Client client = ClientParser.parse(clientUpdated);
        client.setId(id);

        return saveClient(client);
    }

    public Mono<Void> delete(String id) {
        findById(id);
        return clientRepository.deleteById(id);
    }
}
