package br.com.bookstore.bookstore.client;

import br.com.bookstore.bookstore.client.services.DeleteClientService;
import br.com.bookstore.bookstore.client.services.GetClientAppService;
import br.com.bookstore.bookstore.client.services.ListClientAppService;
import br.com.bookstore.bookstore.client.services.SaveClientService;
import br.com.bookstore.bookstore.client.services.UpdateClientService;
import br.com.bookstore.bookstore.client.v1.ClientControllerV1;
import br.com.bookstore.bookstore.exceptions.ClientNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
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

import java.nio.file.Files;
import java.nio.file.Paths;

import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    @DisplayName("findById throws ClientNotFoundException when client is not found")
    void findByIdClient_ThrowClientNotFoundException_WhenClientNotFound() throws Exception {

        when(getClientAppService.findById(anyLong())).thenThrow(new ClientNotFoundException());

        mockMvc.perform(get(URL_CLIENT + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getClientAppService).findById(1L);
    }

    @Test
    @DisplayName("listAll returns list of client when successful")
    void listAll_ReturnsListOfClients_WhenSuccessfull() throws Exception {

        when(listClientAppService.findAll()).thenReturn(Lists.newArrayList(
                createClient().id(1L).build(),
                createClient().id(2L).build(),
                createClient().id(3L).build()
        ));

        mockMvc.perform(get(URL_CLIENT).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)));

        verify(listClientAppService).findAll();
    }

    @Test
    @DisplayName("save returns client when successful")
    void save_ReturnsClient_WhenSuccessful() throws Exception{

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("clientDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(saveClientService).insert(any(Client.class));
    }

    @Test
    @DisplayName("save throws client when name is empty")
    void save_ThrowBadRequest_WhenNameIsEmpty() throws Exception{

        Client client = createClient().id(1L).name("").build();

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("save throws client when phone is empty")
    void save_ThrowBadRequest_WhenPhoneIsEmpty() throws Exception{

        Client client = createClient().id(1L).phone("").build();

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("save throws client when Email is empty")
    void save_ThrowBadRequest_WhenEmailIsEmpty() throws Exception{

        Client client = createClient().id(1L).email("").build();

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("update client when successful")
    void update_ReturnsClientUpdate_WhenSuccessful() throws Exception{

        mockMvc.perform(put(URL_CLIENT + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("clientUpdate.json")))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(updateClientService).update(any(Client.class), eq(1L));
    }

    @Test
    @DisplayName("delete remove clients when successful")
    void delete_RemoveClient_WhenSuccessful() throws Exception{
        mockMvc.perform(delete(URL_CLIENT + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteClientService).delete(anyLong());
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/dataJson/" + file).toAbsolutePath());
        return new String(bytes);
    }
}