# CRUD KTP - Spring Boot & jQuery Ajax

Proyek ini adalah aplikasi manajemen data Kartu Tanda Penduduk (KTP) menggunakan arsitektur Client-Server. Backend dibangun menggunakan **Spring Boot** dan **MySQL**, sedangkan frontend dibangun menggunakan **HTML, CSS, JavaScript**, dan **jQuery Ajax**.

## 🛠 Teknologi yang Digunakan
- **Backend:** Java 17, Spring Boot 3, Spring Data JPA, Hibernate, MySQL Connector.
- **Frontend:** HTML5, CSS3, JavaScript, jQuery (untuk AJAX).
- **Database:** MySQL.
- **IDE:** IntelliJ IDEA Community Edition.

## 🚀 Cara Menjalankan Proyek

1. **Persiapan Database:**
   - Buka MySQL (misalnya melalui XAMPP phpMyAdmin atau MySQL Workbench).
   - Buat database baru bernama `spring`.
     ```sql
     CREATE DATABASE spring;
     ```
   - *Catatan: Tabel KTP akan otomatis dibuat oleh Hibernate saat aplikasi dijalankan (berkat properti `spring.jpa.hibernate.ddl-auto=update`).*

2. **Menjalankan Backend (Spring Boot):**
   - Buka folder proyek (`CRUDKTP`) di IntelliJ IDEA.
   - Tunggu hingga Maven selesai mengunduh semua dependency.
   - Buka file `src/main/java/com/ktp/crud/CrudKtpApplication.java`.
   - Jalankan fungsi `main` (Run `CrudKtpApplication`).
   - Server akan berjalan di `http://localhost:8080`.

3. **Mengakses Frontend:**
   - Setelah server berjalan, buka browser dan akses URL berikut:
     **[http://localhost:8080/](http://localhost:8080/)**
   - Halaman web sudah terintegrasi dan siap digunakan.

---

## 📚 Dokumentasi REST API

Endpoint Base URL: `http://localhost:8080/ktp`

### 1. Tambah Data KTP Baru (Create)
- **URL:** `/ktp`
- **Method:** `POST`
- **Body Request (JSON):**
  ```json
  {
      "nomorKtp": "1234567890123456",
      "namaLengkap": "John Doe",
      "alamat": "Jl. Kemerdekaan No. 45",
      "tanggalLahir": "1990-01-01",
      "jenisKelamin": "Laki-Laki"
  }
  ```
- **Response Success (201 Created):**
  ```json
  {
      "status": "success",
      "message": "Data KTP berhasil ditambahkan",
      "data": { ... }
  }
  ```

### 2. Ambil Seluruh Data KTP (Read All)
- **URL:** `/ktp`
- **Method:** `GET`
- **Response Success (200 OK):**
  ```json
  {
      "status": "success",
      "message": "Berhasil mengambil semua data KTP",
      "data": [
          { "id": 1, "nomorKtp": "1234567890123456", ... }
      ]
  }
  ```

### 3. Ambil Data KTP Berdasarkan ID (Read One)
- **URL:** `/ktp/{id}`
- **Method:** `GET`
- **Response Success (200 OK):**
  ```json
  {
      "status": "success",
      "message": "Berhasil mengambil data KTP",
      "data": { "id": 1, "nomorKtp": "1234567890123456", ... }
  }
  ```

### 4. Perbarui Data KTP (Update)
- **URL:** `/ktp/{id}`
- **Method:** `PUT`
- **Body Request (JSON):**
  ```json
  {
      "nomorKtp": "1234567890123456",
      "namaLengkap": "John Doe Updated",
      "alamat": "Jl. Kemerdekaan No. 45 Blok B",
      "tanggalLahir": "1990-01-01",
      "jenisKelamin": "Laki-Laki"
  }
  ```
- **Response Success (200 OK):**
  ```json
  {
      "status": "success",
      "message": "Data KTP berhasil diperbarui",
      "data": { ... }
  }
  ```

### 5. Hapus Data KTP (Delete)
- **URL:** `/ktp/{id}`
- **Method:** `DELETE`
- **Response Success (200 OK):**
  ```json
  {
      "status": "success",
      "message": "Data KTP berhasil dihapus",
      "data": null
  }
  ```

---

## 📷 Screenshot Tampilan Website

*Silakan ganti placeholder ini dengan screenshot aplikasi yang telah Anda ambil.*

### 1. Form Input dan Tabel Kosong
![Form Input KTP](screenshot-placeholder-1.png)

### 2. Tabel Dengan Data (Read & List)
![Data KTP Terisi](screenshot-placeholder-2.png)

### 3. Proses Edit Data / Notifikasi Ajax
![Notifikasi KTP](screenshot-placeholder-3.png)
