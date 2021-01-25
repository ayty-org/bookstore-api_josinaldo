package br.com.bookstore.bookstore.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "tb_client")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private String phone;

    private String email;

    private String sexo;

    public static Client to(ClientDTO dto) {
         return Client
                 .builder()
                 .name(dto.getName())
                 .age(dto.getAge())
                 .phone(dto.getPhone())
                 .email(dto.getEmail())
                 .sexo(dto.getSexo())
                 .build();
    }
}
