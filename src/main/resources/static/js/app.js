$(document).ready(function() {
    const API_URL = '/ktp';

    // Load initial data
    loadKtpData();

    // Form submit handler (Create / Update)
    $('#ktpForm').on('submit', function(e) {
        e.preventDefault();
        saveKtp();
    });

    // Cancel edit handler
    $('#btnCancel').on('click', function() {
        resetForm();
    });

    // Function to load all data
    function loadKtpData() {
        $.ajax({
            url: API_URL,
            type: 'GET',
            success: function(response) {
                if(response.status === 'success') {
                    renderTable(response.data);
                } else {
                    showMessage(response.message, 'error');
                }
            },
            error: function(xhr) {
                showMessage('Gagal memuat data!', 'error');
            }
        });
    }

    // Function to render table
    function renderTable(dataList) {
        const tbody = $('#ktpTable tbody');
        tbody.empty();
        
        if (dataList.length === 0) {
            tbody.append('<tr><td colspan="7" style="text-align:center;">Tidak ada data KTP.</td></tr>');
            return;
        }

        dataList.forEach((ktp, index) => {
            const row = `
                <tr>
                    <td>${index + 1}</td>
                    <td>${ktp.nomorKtp}</td>
                    <td>${ktp.namaLengkap}</td>
                    <td>${ktp.alamat}</td>
                    <td>${ktp.tanggalLahir}</td>
                    <td>${ktp.jenisKelamin}</td>
                    <td>
                        <button class="btn btn-edit btn-sm" onclick="editKtp(${ktp.id})">Edit</button>
                        <button class="btn btn-delete btn-sm" onclick="deleteKtp(${ktp.id})">Hapus</button>
                    </td>
                </tr>
            `;
            tbody.append(row);
        });
    }

    // Function to save (POST) or update (PUT)
    function saveKtp() {
        const id = $('#ktpId').val();
        
        const payload = {
            nomorKtp: $('#nomorKtp').val(),
            namaLengkap: $('#namaLengkap').val(),
            alamat: $('#alamat').val(),
            tanggalLahir: $('#tanggalLahir').val(),
            jenisKelamin: $('#jenisKelamin').val()
        };

        const isUpdate = id !== "";
        const type = isUpdate ? 'PUT' : 'POST';
        const url = isUpdate ? `${API_URL}/${id}` : API_URL;

        $.ajax({
            url: url,
            type: type,
            contentType: 'application/json',
            data: JSON.stringify(payload),
            success: function(response) {
                if(response.status === 'success') {
                    showMessage(response.message, 'success');
                    resetForm();
                    loadKtpData();
                } else {
                    showMessage(response.message, 'error');
                }
            },
            error: function(xhr) {
                let errorMsg = 'Gagal menyimpan data!';
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMsg = xhr.responseJSON.message;
                    if(xhr.responseJSON.errors) {
                        // Extract validation error messages
                        const errs = Object.values(xhr.responseJSON.errors).join(', ');
                        errorMsg += ': ' + errs;
                    }
                }
                showMessage(errorMsg, 'error');
            }
        });
    }

    // Export function to global scope so HTML onclick can access it
    window.editKtp = function(id) {
        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'GET',
            success: function(response) {
                if(response.status === 'success') {
                    const ktp = response.data;
                    $('#ktpId').val(ktp.id);
                    $('#nomorKtp').val(ktp.nomorKtp);
                    $('#namaLengkap').val(ktp.namaLengkap);
                    $('#alamat').val(ktp.alamat);
                    $('#tanggalLahir').val(ktp.tanggalLahir);
                    $('#jenisKelamin').val(ktp.jenisKelamin);
                    
                    $('#formTitle').text('Edit Data KTP');
                    $('#btnSave').text('Perbarui');
                    $('#btnCancel').show();
                    
                    // Scroll to form
                    $('html, body').animate({scrollTop: 0}, 'fast');
                } else {
                    showMessage(response.message, 'error');
                }
            },
            error: function() {
                showMessage('Gagal mengambil data untuk diubah!', 'error');
            }
        });
    };

    // Export delete function to global scope
    window.deleteKtp = function(id) {
        if(confirm('Apakah Anda yakin ingin menghapus data KTP ini?')) {
            $.ajax({
                url: `${API_URL}/${id}`,
                type: 'DELETE',
                success: function(response) {
                    if(response.status === 'success') {
                        showMessage(response.message, 'success');
                        loadKtpData();
                        if($('#ktpId').val() == id) {
                            resetForm();
                        }
                    } else {
                        showMessage(response.message, 'error');
                    }
                },
                error: function() {
                    showMessage('Gagal menghapus data!', 'error');
                }
            });
        }
    };

    // Helper functions
    function resetForm() {
        $('#ktpForm')[0].reset();
        $('#ktpId').val('');
        $('#formTitle').text('Tambah Data KTP');
        $('#btnSave').text('Simpan');
        $('#btnCancel').hide();
    }

    function showMessage(msg, type) {
        const msgDiv = $('#message');
        msgDiv.text(msg);
        msgDiv.removeClass('success error');
        msgDiv.addClass(type);
        msgDiv.show();
        
        // Auto hide after 5 seconds
        setTimeout(function() {
            msgDiv.fadeOut('slow');
        }, 5000);
    }
});
