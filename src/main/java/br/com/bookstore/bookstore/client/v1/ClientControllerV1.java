package br.com.bookstore.bookstore.client.v1;

import br.com.bookstore.bookstore.client.ClientDTO;
import br.com.bookstore.bookstore.client.services.GetAllClientAppService;
import br.com.bookstore.bookstore.client.services.GetClientAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/client")
public class ClientControllerV1 {

    private final GetClientAppService getClientAppService;
    private final GetAllClientAppService getAllClientAppService;

    @GetMapping(value = "/{id}") //list user by id
    public ClientDTO find(@PathVariable Long id) {
        return ClientDTO.from(getClientAppService.findById(id));
    }

    @GetMapping //list all users
    public List<ClientDTO> findAll() {
        return ClientDTO.fromAll(getAllClientAppService.findAll());
    }
}
