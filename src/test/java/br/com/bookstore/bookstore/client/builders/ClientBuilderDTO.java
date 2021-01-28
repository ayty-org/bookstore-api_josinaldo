package br.com.bookstore.bookstore.client.builders;

import br.com.bookstore.bookstore.client.ClientDTO;

public class ClientBuilderDTO {

    public static ClientDTO.Builder createClientDTO() {
        return ClientDTO.builder()
                .id(1L)
                .name("Shinhigami")
                .age(22)
                .email("teste@email")
                .phone("teste-phone")
                .sexo("masculino");
    }
}
