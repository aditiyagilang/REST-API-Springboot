package com.seleksimagang.models.entities;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;



@Embeddable
public class ProyekLokasiId implements Serializable {

    @ManyToOne
    private Proyek proyek;

    @ManyToOne
    private Lokasi lokasi;


    public ProyekLokasiId() {}


    public ProyekLokasiId(Proyek proyek, Lokasi lokasi) {
        this.proyek = proyek;
        this.lokasi = lokasi;
    }

    // Getters and setters
    public Proyek getProyek() {
        return proyek;
    }

    public void setProyek(Proyek proyek) {
        this.proyek = proyek;
    }

    public Lokasi getLokasi() {
        return lokasi;
    }

    public void setLokasi(Lokasi lokasi) {
        this.lokasi = lokasi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyekLokasiId that = (ProyekLokasiId) o;
        return Objects.equals(proyek, that.proyek) &&
               Objects.equals(lokasi, that.lokasi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proyek, lokasi);
    }
}
