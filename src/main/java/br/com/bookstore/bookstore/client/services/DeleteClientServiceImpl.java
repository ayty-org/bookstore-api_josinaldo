package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.client.ClientRepository;
import br.com.bookstore.bookstore.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    private final ClientRepository clientRepository;

    @Override
    public void delete(Long id) {
        if(!clientRepository.existsById(id)) {
            throw  new ClientNotFoundException();
        }
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);

        clientRepository.deleteById(id);
    }
}
