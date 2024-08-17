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
    import com.seleksimagang.services.LokasiService;

    @RestController
    @RequestMapping("/lokasi")
    public class LokasiController {

        @Autowired
        private LokasiService lokasiService;

        @PostMapping
        public ResponseEntity<Lokasi> addLokasi(@RequestBody Lokasi lokasi) {
            Lokasi savedLokasi = lokasiService.saveLokasi(lokasi);
            return ResponseEntity.ok(savedLokasi);
        }

        @GetMapping
        public ResponseEntity<List<Lokasi>> getAllLokasi() {
            List<Lokasi> lokasiList = lokasiService.getAllLokasi();
            return ResponseEntity.ok(lokasiList);
        }

        @PutMapping
        public ResponseEntity<Lokasi> updateLokasi(@RequestBody Lokasi lokasi) {
            Optional<Lokasi> existingLokasi = lokasiService.getLokasiById(lokasi.getId());
            if (existingLokasi.isPresent()) {
                Lokasi updatedLokasi = lokasiService.saveLokasi(lokasi);
                return ResponseEntity.ok(updatedLokasi);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteLokasi(@PathVariable Long id) {
            lokasiService.deleteLokasi(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Lokasi> getLokasiById(@PathVariable Long id) {
        Optional<Lokasi> lokasi = lokasiService.getLokasiById(id);
        if (lokasi.isPresent()) {
            return ResponseEntity.ok(lokasi.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    }
