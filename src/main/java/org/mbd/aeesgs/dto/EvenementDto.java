package org.mbd.aeesgs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class EvenementDto {
    private Long id;
    private String libelle;
    private String description;
    private String lieu;
    private Date date;
    private Long id_organisateur;
    private Long id_formationCategory;
}
