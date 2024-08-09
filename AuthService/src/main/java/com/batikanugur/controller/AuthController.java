package com.batikanugur.controller;

import com.batikanugur.dto.DoLoginrequestDto;
import com.batikanugur.dto.DoRegisterResponseDto;
import com.batikanugur.dto.DoRegisterrequestDto;
import com.batikanugur.model.Auth;
import com.batikanugur.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.batikanugur.constant.EndPoint.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public  AuthController(AuthService authService) {
        this.authService = authService;
    }
   /*
    //http://http://localhost:9090/
    @GetMapping("/")
    public String hello (){
        return "AuthService Hello";
    }
    //http://http://localhost:9090/info
    @GetMapping("/info")
    public String info (){
        return "Info: AuthService";
    }
*/
   @PostMapping(ENDPOINT_REGISTER)
    public ResponseEntity<DoRegisterResponseDto> register(@RequestBody DoRegisterrequestDto dto){
        return ResponseEntity.ok(authService.doRegister(dto)); }
    @PostMapping(ENDPOINT_LOGIN)
    public ResponseEntity<String> login(@RequestBody DoLoginrequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto)); }
 /*   @GetMapping(ENDPOINT_FINDALL)
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll()); }
*/
    @GetMapping(ENDPOINT_FINDALL)
    public ResponseEntity<List<Auth>> findAll(@RequestParam String token){
        return ResponseEntity.ok(authService.findAll(token)); }

    @GetMapping(ENDPOINT_GETMESSAGE)
    public String getMessage(){
        return null; }

}
