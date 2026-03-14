package com.ktp.crud.controller;

import com.ktp.crud.dto.KtpRequest;
import com.ktp.crud.dto.KtpResponse;
import com.ktp.crud.service.KtpService;
import com.ktp.crud.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ktp")
@CrossOrigin(origins = "*")
public class KtpController {

    private final KtpService ktpService;

    @Autowired
    public KtpController(KtpService ktpService) {
        this.ktpService = ktpService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createKtp(@Valid @RequestBody KtpRequest request) {
        KtpResponse response = ktpService.createKtp(request);
        return new ResponseEntity<>(ApiResponse.success("Data KTP berhasil ditambahkan", response), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllKtp() {
        List<KtpResponse> responses = ktpService.getAllKtp();
        return ResponseEntity.ok(ApiResponse.success("Berhasil mengambil semua data KTP", responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getKtpById(@PathVariable Integer id) {
        KtpResponse response = ktpService.getKtpById(id);
        return ResponseEntity.ok(ApiResponse.success("Berhasil mengambil data KTP", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateKtp(@PathVariable Integer id, @Valid @RequestBody KtpRequest request) {
        KtpResponse response = ktpService.updateKtp(id, request);
        return ResponseEntity.ok(ApiResponse.success("Data KTP berhasil diperbarui", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteKtp(@PathVariable Integer id) {
        ktpService.deleteKtp(id);
        return ResponseEntity.ok(ApiResponse.success("Data KTP berhasil dihapus", null));
    }
}
