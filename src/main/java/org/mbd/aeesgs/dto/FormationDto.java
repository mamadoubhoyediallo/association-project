package org.mbd.aeesgs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class FormationDto {
    private Long id;
    private Double prix;
    private String libelle;
    private String description;
    private String lieu;
    private Date date;
    //private String photo;
    private Long id_organisateur;
    private Long id_formationCategory;
//    private Set<ImageUpload> imageUploads;
}
