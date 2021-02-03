package br.com.bookstore.bookstore.client;

import br.com.bookstore.bookstore.client.services.GetClientAppserviceImpl;
import br.com.bookstore.bookstore.exceptions.ClientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for searching for a client by id ")
class GetClientAppServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    private GetClientAppserviceImpl getClientAppservice;

    @BeforeEach
    void setUp() {
        this.getClientAppservice = new GetClientAppserviceImpl(clientRepositoryMock);
    }

    @Test
    @DisplayName("findById returns client when succesful")
    void findByIdReturnClientWhenSuccessful(){

        Client client = createClient().build(); //create a build to client
        Optional<Client> clientSavedOptional = Optional.of(client);
        when(clientRepositoryMock.findById(anyLong())).thenReturn(clientSavedOptional);

        Client result = this.getClientAppservice.findById(1L); //result of requisition

        //verification
        assertAll("Client",
                () -> assertThat(result.getName(),is("Aktsuki")),
                () -> assertThat(result.getAge(), is(22)),
                () -> assertThat(result.getEmail(),is("teste@email")),
                () -> assertThat(result.getPhone(),is("teste-phone")),
                () -> assertThat(result.getSexo(), is("masculino"))
        );

    }

    @Test
    @DisplayName("findById throws ClientNotFoundException when client is not found")
    void findByIdClientThrowClientNotFoundExceptionWhenClientNotFound() {

        when(clientRepositoryMock.findById(anyLong())).thenThrow(new ClientNotFoundException());

        assertThrows(ClientNotFoundException.class, () -> getClientAppservice.findById(1l));
        verify(clientRepositoryMock, times(1)).findById(anyLong());
    }
}