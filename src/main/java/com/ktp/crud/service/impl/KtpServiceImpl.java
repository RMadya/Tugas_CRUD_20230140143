package com.ktp.crud.service.impl;

import com.ktp.crud.dto.KtpRequest;
import com.ktp.crud.dto.KtpResponse;
import com.ktp.crud.entity.Ktp;
import com.ktp.crud.exception.ResourceNotFoundException;
import com.ktp.crud.mapper.KtpMapper;
import com.ktp.crud.repository.KtpRepository;
import com.ktp.crud.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    private final KtpRepository ktpRepository;
    private final KtpMapper ktpMapper;

    @Autowired
    public KtpServiceImpl(KtpRepository ktpRepository, KtpMapper ktpMapper) {
        this.ktpRepository = ktpRepository;
        this.ktpMapper = ktpMapper;
    }

    @Override
    public KtpResponse createKtp(KtpRequest request) {
        if (ktpRepository.existsByNomorKtp(request.getNomorKtp())) {
            throw new IllegalArgumentException("Nomor KTP sudah terdaftar!");
        }
        Ktp ktp = ktpMapper.toEntity(request);
        Ktp savedKtp = ktpRepository.save(ktp);
        return ktpMapper.toResponse(savedKtp);
    }

    @Override
    public List<KtpResponse> getAllKtp() {
        return ktpRepository.findAll().stream()
                .map(ktpMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public KtpResponse getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data KTP tidak ditemukan dengan id: " + id));
        return ktpMapper.toResponse(ktp);
    }

    @Override
    public KtpResponse updateKtp(Integer id, KtpRequest request) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data KTP tidak ditemukan dengan id: " + id));

        if (ktpRepository.existsByNomorKtpAndIdNot(request.getNomorKtp(), id)) {
            throw new IllegalArgumentException("Nomor KTP sudah digunakan oleh data lain!");
        }

        ktpMapper.updateEntityFromRequest(request, ktp);
        Ktp updatedKtp = ktpRepository.save(ktp);
        return ktpMapper.toResponse(updatedKtp);
    }

    @Override
    public void deleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data KTP tidak ditemukan dengan id: " + id));
        ktpRepository.delete(ktp);
    }
}
