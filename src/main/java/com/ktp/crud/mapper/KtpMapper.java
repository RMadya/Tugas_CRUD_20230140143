package com.ktp.crud.mapper;

import com.ktp.crud.dto.KtpRequest;
import com.ktp.crud.dto.KtpResponse;
import com.ktp.crud.entity.Ktp;
import org.springframework.stereotype.Component;

@Component
public class KtpMapper {

    public Ktp toEntity(KtpRequest request) {
        Ktp ktp = new Ktp();
        ktp.setNomorKtp(request.getNomorKtp());
        ktp.setNamaLengkap(request.getNamaLengkap());
        ktp.setAlamat(request.getAlamat());
        ktp.setTanggalLahir(request.getTanggalLahir());
        ktp.setJenisKelamin(request.getJenisKelamin());
        return ktp;
    }

    public KtpResponse toResponse(Ktp ktp) {
        KtpResponse response = new KtpResponse();
        response.setId(ktp.getId());
        response.setNomorKtp(ktp.getNomorKtp());
        response.setNamaLengkap(ktp.getNamaLengkap());
        response.setAlamat(ktp.getAlamat());
        response.setTanggalLahir(ktp.getTanggalLahir());
        response.setJenisKelamin(ktp.getJenisKelamin());
        return response;
    }

    public void updateEntityFromRequest(KtpRequest request, Ktp ktp) {
        ktp.setNomorKtp(request.getNomorKtp());
        ktp.setNamaLengkap(request.getNamaLengkap());
        ktp.setAlamat(request.getAlamat());
        ktp.setTanggalLahir(request.getTanggalLahir());
        ktp.setJenisKelamin(request.getJenisKelamin());
    }
}
