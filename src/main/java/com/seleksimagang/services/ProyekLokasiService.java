package com.seleksimagang.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seleksimagang.models.entities.Lokasi;
import com.seleksimagang.models.entities.Proyek;
import com.seleksimagang.models.entities.ProyekLokasi;
import com.seleksimagang.models.entities.ProyekLokasiId;
import com.seleksimagang.models.repos.ProyekLokasiRepository;

@Service
public class ProyekLokasiService {
    @Autowired
    private ProyekLokasiRepository proyekLokasiRepository;

    public ProyekLokasi saveProyekLokasi(ProyekLokasi proyekLokasi) {
        return proyekLokasiRepository.save(proyekLokasi);
    }

    

    public Optional<ProyekLokasi> getProyekLokasiWithProyekAndLokasiById(Long proyekId) {
        return proyekLokasiRepository.findProyekLokasiWithProyekAndLokasiById(proyekId);
    }
    public List<ProyekLokasi> getAllProyekLokasiWithProyekAndLokasi() {
        return proyekLokasiRepository.findAll(); // Anda mungkin perlu menyesuaikan dengan query yang sesuai
    }
    
    public void deleteByProyekId(Long proyekId) {
        proyekLokasiRepository.deleteByProyekId(proyekId);
    }
    public Optional<ProyekLokasi> getProyekLokasiByProyekId(Long proyekId) {
        return Optional.ofNullable(proyekLokasiRepository.findByProyekId(proyekId));
    }

    public void deleteProyekLokasi(Proyek proyek, Lokasi lokasi) {
        ProyekLokasiId proyekLokasiId = new ProyekLokasiId(proyek, lokasi);
        Optional<ProyekLokasi> proyekLokasiOptional = proyekLokasiRepository.findById(proyekLokasiId);

        proyekLokasiOptional.ifPresent(proyekLokasi -> proyekLokasiRepository.delete(proyekLokasi));
    }
}


