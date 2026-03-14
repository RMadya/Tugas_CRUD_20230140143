package com.ktp.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public class KtpRequest {

    @NotBlank(message = "Nomor KTP tidak boleh kosong")
    @Size(min = 16, max = 16, message = "Nomor KTP harus 16 karakter")
    private String nomorKtp;

    @NotBlank(message = "Nama Lengkap tidak boleh kosong")
    private String namaLengkap;

    @NotBlank(message = "Alamat tidak boleh kosong")
    private String alamat;

    @NotNull(message = "Tanggal Lahir tidak boleh kosong")
    @Past(message = "Tanggal Lahir harus di masa lampau")
    private LocalDate tanggalLahir;

    @NotBlank(message = "Jenis Kelamin tidak boleh kosong")
    private String jenisKelamin;

    public KtpRequest() {
    }

    public String getNomorKtp() {
        return nomorKtp;
    }

    public void setNomorKtp(String nomorKtp) {
        this.nomorKtp = nomorKtp;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
