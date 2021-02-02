package br.com.bookstore.bookstore.client;

import br.com.bookstore.bookstore.client.services.ListPageClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.Collections;

import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the service responsible for pagination of all client")
class ListPageClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private ListPageClientServiceImpl listPageClientService;

    @BeforeEach
    void setUp() {
        this.listPageClientService = new ListPageClientServiceImpl(clientRepository);
    }

    @Test
    @DisplayName("listAll returns list of client inside page object when successful")
    void listAllReturnsListOfClientInsidePageObjectWhenSuccessful() {

        PageRequest pageable = PageRequest.of(0, 2);

        when(listPageClientService.findPage(pageable))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, createClient().build())));

        Page<Client> result = listPageClientService.findPage(pageable);

        Client client = createClient().build();

        assertAll("Client",
                ()-> assertThat(result.getNumber(), is(0)),
                ()-> assertThat(result.getTotalElements(), is(2L)),
                ()-> assertThat(result.getTotalPages(), is(1)),
                ()-> assertThat(result.getSize(), is(2)),
                ()-> assertThat(result.getContent().get(0).getName(), is(client.getName())),
                () -> assertThat(result.getContent().get(0).getAge(), is(client.getAge())),
                () -> assertThat(result.getContent().get(0).getEmail(), is(client.getEmail())),
                () -> assertThat(result.getContent().get(0).getPhone(), is(client.getPhone())),
                () -> assertThat(result.getContent().get(0).getSexo(), is(client.getSexo())),


                ()-> assertThat(result.getContent().get(1).getName(), is(client.getName())),
                () -> assertThat(result.getContent().get(1).getAge(), is(client.getAge())),
                () -> assertThat(result.getContent().get(1).getEmail(), is(client.getEmail())),
                () -> assertThat(result.getContent().get(1).getPhone(), is(client.getPhone())),
                () -> assertThat(result.getContent().get(1).getSexo(), is(client.getSexo()))
        );
    }
}