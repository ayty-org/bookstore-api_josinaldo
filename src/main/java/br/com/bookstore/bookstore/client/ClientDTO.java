package br.com.bookstore.bookstore.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private Integer phone;

    @NotNull
    private String email;

    @NotNull
    private String sexo;

    public static ClientDTO from(Client entity) {
        return ClientDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .sexo(entity.getSexo())
                .build();
    }

    public static List<ClientDTO> fromAll(List<Client> clients) {
        return clients.stream().map(ClientDTO::from).collect(Collectors.toList());
    }

}
