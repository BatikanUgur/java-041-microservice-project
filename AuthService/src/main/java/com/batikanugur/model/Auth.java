package com.batikanugur.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@EqualsAndHashCode(callSuper = true)
@SuperBuilder // bir sınıftan nesne türetmek için kullanılır
@Data // set ve get modetodlarını tanımlar
@NoArgsConstructor // boş parametreli constructor
@AllArgsConstructor // dolu parametreli constructor

@ToString //nesne bilgisini yazdırmak için

@Entity
@Table(name = "auth")
public class Auth extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String email;
    private String password;

}
