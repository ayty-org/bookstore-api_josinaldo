package br.com.bookstore.bookstore.client;

import br.com.bookstore.bookstore.client.services.DeleteClientService;
import br.com.bookstore.bookstore.client.services.GetClientAppService;
import br.com.bookstore.bookstore.client.services.ListClientAppService;
import br.com.bookstore.bookstore.client.services.ListPageClientService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

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
    private ListPageClientService listPageClientService;

    @MockBean
    private UpdateClientService updateClientService;

    @MockBean
    private DeleteClientService deleteClientService;

    @MockBean
    private SaveClientService saveClientService;

    @Test
    @DisplayName("findById returns client when succesful")
    void findByIdReturnClientWhenSuccessful() throws Exception{

        when(getClientAppService.findById(anyLong())).thenReturn(createClient().build());

        mockMvc.perform(get(URL_CLIENT + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Aktsuki")))
                .andExpect(jsonPath("$.age", is(22)))
                .andExpect(jsonPath("$.email", is("teste@email")))
                .andExpect(jsonPath("$.phone", is("teste-phone")))
                .andExpect(jsonPath("$.sexo", is("masculino")));

        verify(getClientAppService).findById(anyLong());
    }

    @Test
    @DisplayName("findById throws ClientNotFoundException when client is not found")
    void findByIdClientThrowClientNotFoundExceptionWhenClientNotFound() throws Exception {

        when(getClientAppService.findById(anyLong())).thenThrow(new ClientNotFoundException());

        mockMvc.perform(get(URL_CLIENT + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getClientAppService).findById(1L);
    }

    @Test
    @DisplayName("listAll returns list of client when successful")
    void listAllReturnsListOfClientsWhenSuccessfull() throws Exception {

        when(listClientAppService.findAll()).thenReturn(Lists.newArrayList(
                createClient().id(1L).build(),
                createClient().id(2L).build()
        ));

        mockMvc.perform(get(URL_CLIENT).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Aktsuki")))
                .andExpect(jsonPath("$[0].age", is(22)))
                .andExpect(jsonPath("$[0].email",is("teste@email")))
                .andExpect(jsonPath("$[0].phone",  is("teste-phone")))
                .andExpect(jsonPath("$[0].sexo", is("masculino")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Aktsuki")))
                .andExpect(jsonPath("$[1].age",  is(22)))
                .andExpect(jsonPath("$[1].email", is("teste@email")))
                .andExpect(jsonPath("$[1].phone", is("teste-phone")))
                .andExpect(jsonPath("$[1].sexo", is("masculino")));


        verify(listClientAppService).findAll();
    }

    @Test
    @DisplayName("listAll returns list of client inside page object when successful")
    void listAllReturnsListOfClientInsidePageObject_WhenSuccessful() throws Exception{

        Page<Client> clientPage = new PageImpl<>(Collections.singletonList(createClient().id(1L).build()));

        Pageable pageable = PageRequest.of(0,2);

        when(listPageClientService.findPage(pageable)).thenReturn(clientPage);

        mockMvc.perform(get(URL_CLIENT + "/page/?page=0&size=2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id",   is(1)))
                .andExpect(jsonPath("$.content[0].name", is("Aktsuki")))
                .andExpect(jsonPath("$.content[0].age",  is(22)))
                .andExpect(jsonPath("$.content[0].email",is("teste@email")))
                .andExpect(jsonPath("$.content[0].phone",is("teste-phone")))
                .andExpect(jsonPath("$.content[0].sexo", is("masculino")));

        verify(listPageClientService).findPage(pageable);
    }

    @Test
    @DisplayName("save returns client when successful")
    void saveReturnsClientWhenSuccessful() throws Exception{

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("clientDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(saveClientService).insert(any(Client.class));
    }

    @Test
    @DisplayName("save throws client when name is empty")
    void saveThrowBadRequestWhenNameIsEmpty() throws Exception{

        Client client = createClient().id(1L).name("").build();

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("save throws client when phone is empty")
    void saveThrowBadRequestWhenPhoneIsEmpty() throws Exception{

        Client client = createClient().id(1L).phone("").build();

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("save throws client when Email is empty")
    void saveThrowBadRequestWhenEmailIsEmpty() throws Exception{

        Client client = createClient().id(1L).email("").build();

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("update client when successful")
    void updateReturnsClientUpdateWhenSuccessful() throws Exception{

        mockMvc.perform(put(URL_CLIENT + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("clientUpdate.json")))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(updateClientService).update(any(Client.class), eq(1L));
    }

    @Test
    @DisplayName("delete remove clients when successful")
    void deleteRemoveClientWhenSuccessful() throws Exception{
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