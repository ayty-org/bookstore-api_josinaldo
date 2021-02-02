package br.com.bookstore.bookstore.client;

import br.com.bookstore.bookstore.client.services.ListClientAppServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the service responsible for list all client")
class ListClientAppServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private ListClientAppServiceImpl getAllClientAppService;

    @BeforeEach
    void setUp() {
        this.getAllClientAppService = new ListClientAppServiceImpl(clientRepository);
    }

    @Test
    @DisplayName("listAll returns list of client when successful")
    void listAllReturnsListOfClientsWhenSuccessfull() {

        Client client = createClient().build();
        when(clientRepository.findAll()).thenReturn(
                Stream.of(createClient().name("Teste").build()).collect(Collectors.toList())
        );

        List<Client> result = this.getAllClientAppService.findAll();

        assertAll("Client",
                () -> assertThat(result.size(), is(1)),
                () -> assertThat(result.get(0).getName(), is("Teste")),
                () -> assertThat(result.get(0).getAge(), is(client.getAge())),
                () -> assertThat(result.get(0).getEmail(), is(client.getEmail())),
                () -> assertThat(result.get(0).getPhone(), is(client.getPhone())),
                () -> assertThat(result.get(0).getSexo(), is(client.getSexo()))
        );
    }
}