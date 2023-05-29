package ee.gulho.assignment.account.controller;

import ee.gulho.assignment.account.entity.Account;
import ee.gulho.assignment.account.service.AccountService;
import ee.gulho.assignment.account.service.dto.AccountCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account/")
@RequiredArgsConstructor
public class AccountController implements BaseController {

    private final AccountService service;

    @PostMapping
    ResponseEntity<Account> createAccount(@Valid @RequestBody AccountCreateRequest request) {
        return ResponseEntity
                .ok(service.createAccount(request));
    }

    @GetMapping("{id}")
    ResponseEntity<Account> getAccount(@PathVariable String id) {
        return ResponseEntity
                .ok(service.getAccountById((id)));
    }
}
