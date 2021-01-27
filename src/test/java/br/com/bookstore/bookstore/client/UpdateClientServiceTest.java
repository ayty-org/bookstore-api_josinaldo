package br.com.bookstore.bookstore.client;

import br.com.bookstore.bookstore.client.services.UpdateClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the service responsible for update client")
class UpdateClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    private UpdateClientServiceImpl updateClientService;

    @BeforeEach
    void setUp() {
        this.updateClientService = new UpdateClientServiceImpl(clientRepositoryMock);
    }

    @Test
    @DisplayName("update client when successful")
    void update_ReturnsClientUpdate_WhenSuccessful(){

        Client putClientRequest = createClient()
                .name("Name update")
                .age(30)
                .build();

        Client client = createClient().build();
        Optional<Client> clientOptional  = Optional.of(client);
        when(clientRepositoryMock.findById(anyLong())).thenReturn(clientOptional);

        updateClientService.update(putClientRequest, 1L);

        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepositoryMock).save(clientArgumentCaptor.capture());

        Client result = clientArgumentCaptor.getValue();

        assertAll("Client",
                () -> assertThat(result.getName(), is(putClientRequest.getName())),
                () -> assertThat(result.getAge(), is(putClientRequest.getAge())),
                () -> assertThat(result.getEmail(), is(putClientRequest.getEmail())),
                () -> assertThat(result.getPhone(), is(putClientRequest.getPhone())),
                () -> assertThat(result.getSexo(), is(putClientRequest.getSexo()))
        );
    }
}