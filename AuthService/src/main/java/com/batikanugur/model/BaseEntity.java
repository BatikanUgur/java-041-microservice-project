package com.batikanugur.model;


import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass // üst sınıf için kullanılmalıdır

@SuperBuilder // bir sınıftan nesne türetmek için kullanılır
@Data // set ve get modetodlarını tanımlar
@NoArgsConstructor // boş parametreli constructor
@AllArgsConstructor // dolu parametreli constructor
public class BaseEntity {

    private Long createAt;
    private Long updateAt;
    private boolean state;

}
