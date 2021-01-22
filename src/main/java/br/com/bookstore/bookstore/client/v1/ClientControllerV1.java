package br.com.bookstore.bookstore.client.v1;

import br.com.bookstore.bookstore.client.ClientDTO;
import br.com.bookstore.bookstore.client.services.GetClientAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/client")
public class ClientControllerV1 {

    private final GetClientAppService getClientAppService;

    @GetMapping(value = "/{id}")
    public ClientDTO find(@PathVariable Long id) {
        return ClientDTO.from(getClientAppService.findById(id));
    }
}
