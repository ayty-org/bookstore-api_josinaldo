package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.client.ClientRepository;
import br.com.bookstore.bookstore.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateClientServiceImpl implements UpdateClientService {

    private final ClientRepository clientRepository;

    @Override
    public void update(Client client, Long id) {
        Client savedClient = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);

        savedClient.setName(client.getName());
        savedClient.setAge(client.getAge());
        savedClient.setEmail(client.getEmail());
        savedClient.setPhone(client.getPhone());
        savedClient.setSexo(client.getSexo());

        clientRepository.save(savedClient);
    }
}
