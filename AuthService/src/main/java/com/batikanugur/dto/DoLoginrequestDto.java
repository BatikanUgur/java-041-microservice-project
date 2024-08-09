package com.batikanugur.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // bir sınıfın kolayca nesne oluşturmasını sağlar
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoLoginrequestDto {
    private String username;
    private String password;

}
