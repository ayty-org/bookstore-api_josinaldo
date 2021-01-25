package br.com.bookstore.bookstore.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
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

    @NotNull(message = "The client name cannot be null")
    private String name;

    @NotNull(message = "The client age cannot be null")
    @Min(value = 1)
    private Integer age;

    @NotNull(message = "The client phone cannot be null")
    private String phone;

    @NotNull(message = "The client email cannot be null")
    private String email;

    @NotNull(message = "The client sexo cannot be null")
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
