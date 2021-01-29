package br.com.bookstore.bookstore.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "The client name cannot be empty")
    private String name;

    @NotNull(message = "The client age cannot be null")
    @Min(value = 1)
    private Integer age;

    @NotEmpty(message = "The client phone cannot be empty")
    private String phone;

    @NotEmpty(message = "The client email cannot be empty")
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
