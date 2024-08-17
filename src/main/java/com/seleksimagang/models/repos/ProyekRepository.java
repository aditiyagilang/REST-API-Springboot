package com.seleksimagang.models.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seleksimagang.models.entities.Proyek;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Long> {
    @Query("SELECT p FROM Proyek p LEFT JOIN ProyekLokasi pl ON p.id = pl.proyek.id LEFT JOIN pl.lokasi l WHERE p.id = :proyekId")
    Optional<Proyek> findProyekWithLokasiById(@Param("proyekId") Long proyekId);
}

