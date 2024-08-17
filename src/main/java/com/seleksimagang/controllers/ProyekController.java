package com.seleksimagang.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seleksimagang.models.entities.Lokasi;
import com.seleksimagang.models.entities.Proyek;
import com.seleksimagang.models.entities.ProyekLokasi;
import com.seleksimagang.models.request.ProyekRequest;
import com.seleksimagang.services.LokasiService;
import com.seleksimagang.services.ProyekLokasiService;
import com.seleksimagang.services.ProyekService;
@RestController
@RequestMapping("/proyek")
public class ProyekController {

    @Autowired
    private ProyekLokasiService proyekLokasiService;

    @Autowired
    private ProyekService proyekService;

    @Autowired
    private LokasiService lokasiService;

    @PostMapping
    public ResponseEntity<Proyek> addProyek(@RequestBody ProyekRequest proyekRequest) {
        Proyek proyek = new Proyek();
        proyek.setNamaProyek(proyekRequest.getNamaProyek());
        proyek.setClient(proyekRequest.getClient());
        proyek.setTglMulai(proyekRequest.getTglMulai());
        proyek.setTglSelesai(proyekRequest.getTglSelesai());
        proyek.setPimpinanProyek(proyekRequest.getPimpinanProyek());
        proyek.setKeterangan(proyekRequest.getKeterangan());
        proyek.setCreatedAt(proyekRequest.getCreatedAt());

        Proyek savedProyek = proyekService.saveProyek(proyek);

        if (proyekRequest.getLokasiId() != null) {
            Lokasi lokasi = new Lokasi();
            lokasi.setId(proyekRequest.getLokasiId());
            ProyekLokasi proyekLokasi = new ProyekLokasi();
            proyekLokasi.setProyek(savedProyek);
            proyekLokasi.setLokasi(lokasi);
            proyekLokasiService.saveProyekLokasi(proyekLokasi);
        }

        return ResponseEntity.ok(savedProyek);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Proyek> getProyekById(@PathVariable Long id) {
        Optional<Proyek> proyek = proyekService.getProyekById(id);
        return proyek.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProyekLokasi>> getAllProyekWithLokasi() {
        List<ProyekLokasi> proyekLokasiList = proyekLokasiService.getAllProyekLokasiWithProyekAndLokasi();
        return ResponseEntity.ok(proyekLokasiList);
    }

  
    @PutMapping
    public ResponseEntity<Proyek> updateProyek(@RequestBody ProyekRequest proyekRequest) {
        Long proyekId = proyekRequest.getId();
        Optional<Proyek> existingProyekOptional = proyekService.getProyekById(proyekId);
    
        if (existingProyekOptional.isPresent()) {
            Proyek proyek = existingProyekOptional.get();
    
            proyek.setNamaProyek(proyekRequest.getNamaProyek());
            proyek.setClient(proyekRequest.getClient());
            proyek.setTglMulai(proyekRequest.getTglMulai());
            proyek.setTglSelesai(proyekRequest.getTglSelesai());
            proyek.setPimpinanProyek(proyekRequest.getPimpinanProyek());
            proyek.setKeterangan(proyekRequest.getKeterangan());
            proyek.setCreatedAt(proyekRequest.getCreatedAt());
    
            proyekService.saveProyek(proyek);
    
            Optional<ProyekLokasi> proyekLokasiOptional = proyekLokasiService.getProyekLokasiByProyekId(proyekId);
            Lokasi lokasi = lokasiService.getLokasiById(proyekRequest.getLokasiId()).orElse(null);
    
            if (lokasi != null) {
                if (proyekLokasiOptional.isPresent()) {
                    ProyekLokasi proyekLokasi = proyekLokasiOptional.get();
                    proyekLokasiService.deleteProyekLokasi(proyekLokasi.getProyek(), proyekLokasi.getLokasi());
                }
    
                ProyekLokasi proyekLokasiBaru = new ProyekLokasi();
                proyekLokasiBaru.setProyek(proyek);
                proyekLokasiBaru.setLokasi(lokasi);
                proyekLokasiService.saveProyekLokasi(proyekLokasiBaru);
    
                return ResponseEntity.ok(proyek);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<Void> deleteProyek(@RequestBody ProyekRequest proyekRequest) {
        Long id = proyekRequest.getId(); 
        
        Optional<Proyek> existingProyekOptional = proyekService.getProyekById(id);

        if (existingProyekOptional.isPresent()) {
            proyekLokasiService.deleteByProyekId(id);
            proyekService.deleteProyek(id);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}
