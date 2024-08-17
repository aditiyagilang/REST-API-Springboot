package com.seleksimagang.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seleksimagang.models.entities.Proyek;
import com.seleksimagang.models.repos.ProyekRepository;
@Service
public class ProyekService {
    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private ProyekLokasiService proyekLokasiService;

    public Proyek saveProyek(Proyek proyek) {
        return proyekRepository.save(proyek);
    }

    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    public Optional<Proyek> getProyekById(Long id) {
        return proyekRepository.findProyekWithLokasiById(id);
    }

    public void deleteProyek(Long id) {
        proyekLokasiService.deleteByProyekId(id);
        proyekRepository.deleteById(id);
    }
}
