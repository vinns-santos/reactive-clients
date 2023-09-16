package tech.ada.pw3.management.clients.dto.parser;

import tech.ada.pw3.management.clients.dto.ClientDTO;
import tech.ada.pw3.management.clients.model.Client;

public class ClientParser {
    public static Client parse(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setAge(clientDTO.getAge());
        client.setEmail(clientDTO.getEmail());
        return client;
    }
}
