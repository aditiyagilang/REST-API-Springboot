package com.seleksimagang.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seleksimagang.models.entities.Lokasi;
import com.seleksimagang.models.repos.LokasiRepository;

@Service
public class LokasiService {

    @Autowired
    private LokasiRepository lokasiRepository;

    public Lokasi saveLokasi(Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    public Optional<Lokasi> getLokasiById(Long id) {
        return lokasiRepository.findById(id);
    }

    public void deleteLokasi(Long id) {
        lokasiRepository.deleteById(id);
    }
}
