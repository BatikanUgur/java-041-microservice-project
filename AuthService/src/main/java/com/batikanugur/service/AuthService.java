package com.batikanugur.service;

import com.batikanugur.dto.DoLoginrequestDto;
import com.batikanugur.dto.DoRegisterResponseDto;
import com.batikanugur.dto.DoRegisterrequestDto;
import com.batikanugur.exception.AuthServiceException;
import com.batikanugur.exception.ErrorType;
import com.batikanugur.model.Auth;
import com.batikanugur.repository.IAuthRepository;
import com.batikanugur.utils.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository repository;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(JpaRepository<Auth, Long> repository, IAuthRepository repository1, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository1;
        this.jwtTokenManager = jwtTokenManager;
    }



    public String doLogin(DoLoginrequestDto dto) {

    Optional<Auth> auth = repository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());


    if(!auth.isPresent()){
        throw new AuthServiceException(ErrorType.DOLOGIN_USERNAME_OR_PASSWORD);
    }

        //return auth.get().getId().toString();

        return jwtTokenManager.createToken(Long.valueOf(auth.get().getId().toString())).get();
    }

    //DoRegisterResponseDto
    public DoRegisterResponseDto doRegister(DoRegisterrequestDto dto) {


        if(!dto.getPassword().equals(dto.getRePassword())){
            throw new AuthServiceException(ErrorType.REGISTER_PARAMETER_MISMATCH);
        }

        Auth auth = new Auth();
        auth.setUsername(dto.getUsername());
        auth.setEmail(dto.getEmail());
        auth.setPassword(dto.getPassword());
        auth.setCreateAt(System.currentTimeMillis());
        save(auth);

/*
        Auth auth= save(Auth.builder()
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .state(true)
                        .createAt(System.currentTimeMillis())
                        .build());
        */

        DoRegisterResponseDto responseDto = new DoRegisterResponseDto();
        responseDto.setId(auth.getId());
        responseDto.setUserName(auth.getUsername());
        responseDto.setEmail(auth.getEmail());
        return responseDto;
    }


    public List<Auth> findAll(String token){

        Optional<Long> id = null;

        try {
            id = jwtTokenManager.getIdInfoFromToken(token);
        }catch (Exception e) {
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }

        if(findById(id.get()).isEmpty()){
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }

        return repository.findAll();
    }


}
