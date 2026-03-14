package com.ktp.crud.service;

import com.ktp.crud.dto.KtpRequest;
import com.ktp.crud.dto.KtpResponse;

import java.util.List;

public interface KtpService {
    KtpResponse createKtp(KtpRequest request);
    List<KtpResponse> getAllKtp();
    KtpResponse getKtpById(Integer id);
    KtpResponse updateKtp(Integer id, KtpRequest request);
    void deleteKtp(Integer id);
}
