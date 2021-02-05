package br.com.bookstore.bookstore.client;

import br.com.bookstore.bookstore.client.services.DeleteClientServiceImpl;
import br.com.bookstore.bookstore.exceptions.ClientNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for delete client")
class DeleteClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    private DeleteClientServiceImpl deleteClientService;

    @BeforeEach
    void setUp() {
        this.deleteClientService = new DeleteClientServiceImpl(clientRepositoryMock);
    }

    @Test
    @DisplayName("delete remove clients when successful")
    void deleteRemoveClientWhenSuccessful() {

        when(clientRepositoryMock.existsById(anyLong())).thenReturn(true);
        deleteClientService.delete(1L);
        verify(clientRepositoryMock).existsById(anyLong());
        verify(clientRepositoryMock).deleteById(anyLong());
    }
    @Test
    @DisplayName("delete throws ClientNotFoundException when client is not found")
    void deleteThrowClientNotFoundExceptionWhenClientNotFound() {

        when(clientRepositoryMock.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrows(ClientNotFoundException.class, ()-> deleteClientService.delete(1L));

        verify(clientRepositoryMock, times(0)).deleteById(anyLong());
    }
}