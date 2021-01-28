package br.com.bookstore.bookstore.client;

import br.com.bookstore.bookstore.client.services.DeleteClientService;
import br.com.bookstore.bookstore.client.services.GetClientAppService;
import br.com.bookstore.bookstore.client.services.ListClientAppService;
import br.com.bookstore.bookstore.client.services.SaveClientService;
import br.com.bookstore.bookstore.client.services.UpdateClientService;
import br.com.bookstore.bookstore.client.v1.ClientControllerV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientControllerV1.class)
@DisplayName("Validates the functionality of the controller responsible of client")
class ClientControllerV1Test {

    private final String URL_CLIENT = "/v1/api/client";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetClientAppService getClientAppService;

    @MockBean
    private ListClientAppService listClientAppService;

    @MockBean
    private UpdateClientService updateClientService;

    @MockBean
    private DeleteClientService deleteClientService;

    @MockBean
    private SaveClientService saveClientService;

    @Test
    @DisplayName("findById returns client when succesful")
    void findById_ReturnClient_WhenSuccessful() throws Exception{

        when(getClientAppService.findById(anyLong())).thenReturn(createClient().build());

        Client client = createClient().build();

        mockMvc.perform(get(URL_CLIENT + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.age", is(client.getAge())))
                .andExpect(jsonPath("$.email", is(client.getEmail())))
                .andExpect(jsonPath("$.phone", is(client.getPhone())))
                .andExpect(jsonPath("$.sexo", is(client.getSexo())));

        verify(getClientAppService).findById(anyLong());
    }
}