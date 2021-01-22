package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveClientServiceImpl implements SaveClientService {

    private final ClientRepository clientRepository;
    @Override
    public void insert(Client client) {
        clientRepository.save(client);
    }
}
