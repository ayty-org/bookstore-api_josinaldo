package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetClientAppserviceImpl implements GetClientAppService {

    private final ClientRepository clientRepository;

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id);
    }
}
