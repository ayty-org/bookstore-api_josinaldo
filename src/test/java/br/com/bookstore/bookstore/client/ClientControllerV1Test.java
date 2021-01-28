package br.com.bookstore.bookstore.client;

import br.com.bookstore.bookstore.client.services.GetAllClientAppService;
import br.com.bookstore.bookstore.client.services.GetClientAppService;
import br.com.bookstore.bookstore.client.services.SaveClientService;
import br.com.bookstore.bookstore.client.services.UpdateClientService;
import br.com.bookstore.bookstore.client.v1.ClientControllerV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientControllerV1.class)
@DisplayName("Validates the functionality of the controller responsible of client")
class ClientControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetClientAppService getClientAppService;

    @MockBean
    private GetAllClientAppService getAllClientAppService;

    @MockBean
    private UpdateClientService updateClientService;

    @MockBean
    private SaveClientService saveClientService;

    @MockBean
    private DeleteClientServiceTest deleteClientServiceTest;


}