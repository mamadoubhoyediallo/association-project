package org.mbd.aeesgs.repositories;

import org.mbd.aeesgs.model.ImageUpload;
import org.mbd.aeesgs.model.Organisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageUploadRepo extends JpaRepository<ImageUpload, Long> {
    //Organisateur findByLibelle(String libelle);
}
