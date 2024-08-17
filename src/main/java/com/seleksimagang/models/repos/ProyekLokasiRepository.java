package com.seleksimagang.models.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seleksimagang.models.entities.ProyekLokasi;
import com.seleksimagang.models.entities.ProyekLokasiId;

import jakarta.transaction.Transactional;


@Repository
public interface ProyekLokasiRepository extends JpaRepository<ProyekLokasi, ProyekLokasiId> {
    ProyekLokasi findByProyekId(Long proyekId);
    @Query("SELECT pl FROM ProyekLokasi pl JOIN FETCH pl.proyek p JOIN FETCH pl.lokasi l WHERE pl.proyek.id = :proyekId")
    Optional<ProyekLokasi> findProyekLokasiWithProyekAndLokasiById(@Param("proyekId") Long proyekId);
    @Modifying
    @Transactional
    @Query("DELETE FROM ProyekLokasi pl WHERE pl.proyek.id = :proyekId")
    void deleteByProyekId(Long proyekId);
}


