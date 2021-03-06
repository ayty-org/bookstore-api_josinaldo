package br.com.bookstore.bookstore.client.builders;

import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.client.Sex;

public class ClientBuilder {

    public static Client.Builder createClient() {
        return Client
                .builder()
                .id(1L)
                .name("Aktsuki")
                .age(22)
                .email("teste@email")
                .phone("teste-phone")
                .sexo(Sex.MASCULINO);
    }
}
