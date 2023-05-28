package ee.gulho.assignment.account.controller;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.exception.AccountNotFoundException;
import ee.gulho.assignment.account.service.AccountService;
import ee.gulho.assignment.account.service.dto.AccountCreateRequestDto;
import ee.gulho.assignment.account.service.dto.AccountResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("account/")
@RequiredArgsConstructor
public class AccountController extends BaseController {

    private final AccountService service;

    @PostMapping
    ResponseEntity<Account> createAccount(@Valid @RequestBody AccountCreateRequestDto request) {
        return ResponseEntity
                .ok(service.createAccount(request));
    }

    @GetMapping("{id}")
    ResponseEntity<Account> getAccount(@PathVariable String id) {
        return ResponseEntity
                .ok(service.getAccountById((id)));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountNotFoundException.class)
    ResponseEntity<String> handleAccountNotFound(AccountNotFoundException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}
