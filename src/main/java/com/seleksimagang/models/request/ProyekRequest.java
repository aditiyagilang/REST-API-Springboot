
package com.seleksimagang.models.request;

import java.sql.Timestamp;

public class ProyekRequest {
    private Long id;
    private String namaProyek;
    private String client;
    private Timestamp tglMulai;
    private Timestamp tglSelesai;
    private String pimpinanProyek;
    private String keterangan;
    private Timestamp createdAt;
    private Long lokasiId; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaProyek() {
        return namaProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Timestamp getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(Timestamp tglMulai) {
        this.tglMulai = tglMulai;
    }

    public Timestamp getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(Timestamp tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getPimpinanProyek() {
        return pimpinanProyek;
    }

    public void setPimpinanProyek(String pimpinanProyek) {
        this.pimpinanProyek = pimpinanProyek;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLokasiId() {
        return lokasiId;
    }

    public void setLokasiId(Long lokasiId) {
        this.lokasiId = lokasiId;
    }
}
