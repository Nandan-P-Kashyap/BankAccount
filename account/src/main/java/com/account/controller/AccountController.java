package com.account.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.dto.DebitCreditDto;
import com.account.dto.TransferRequest;
import com.account.entities.AccountEntity;
import com.account.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService svc;

    public AccountController(AccountService svc) { this.svc = svc; }

    @PostMapping
    public ResponseEntity<AccountEntity> create(@RequestBody @Validated AccountEntity account) {
    	AccountEntity saved = svc.createAccount(account);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{acctNo}")
    public ResponseEntity<AccountEntity> get(@PathVariable("acctNo") String acctNo) {
        return ResponseEntity.ok(svc.getByAccountNumber(acctNo));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AccountEntity>> list() {
        return ResponseEntity.ok(svc.listAll());
    }

    @PutMapping("/{acctNo}")
    public ResponseEntity<AccountEntity> update(@PathVariable("acctNo") String acctNo,
                                          @RequestBody AccountEntity update) {
        return ResponseEntity.ok(svc.updateAccount(acctNo, update));
    }

    @DeleteMapping("/{acctNo}")
    public ResponseEntity<Void> delete(@PathVariable("acctNo") String acctNo) {
        svc.deleteAccount(acctNo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountEntity> deposit(@RequestBody @Validated DebitCreditDto req) {
        AccountEntity depositedAccount = svc.deposit(req.getAccountNo(), req.getAmount());
        return ResponseEntity.ok(depositedAccount);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountEntity> withdraw(@RequestBody DebitCreditDto req) {
        return ResponseEntity.ok(svc.withdraw(req.getAccountNo(), req.getAmount()));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody @Validated TransferRequest req) {
        svc.transfer(req.getFromAccount(), req.getToAccount(), req.getAmount());
        return ResponseEntity.ok().build();
    }
}