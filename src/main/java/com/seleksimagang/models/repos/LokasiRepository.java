package com.seleksimagang.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seleksimagang.models.entities.Lokasi;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {
}
