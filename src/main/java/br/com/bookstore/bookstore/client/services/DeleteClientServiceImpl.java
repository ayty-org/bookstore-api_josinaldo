package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    private final ClientRepository clientRepository;
    private final GetClientAppService getClientAppService;

    @Override
    public void delete(Long id) {
        clientRepository.delete(getClientAppService.findById(id));
    }
}
