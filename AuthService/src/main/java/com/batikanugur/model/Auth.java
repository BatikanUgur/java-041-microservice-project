package com.batikanugur.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
    // @Size(min = 1, max= 50)
    @Column(unique = true, nullable = false)
    private String username;
//  @Email
//  @Column(unique = true)
    private String email;
    private String password;

}
