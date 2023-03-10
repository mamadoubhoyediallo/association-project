package org.mbd.aeesgs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor
public class FormationResponseDto {
    private String nom;
    private String imageUrl;

    public FormationResponseDto(String nom, String imageUrl) {
        this.nom = nom;
        this.imageUrl = imageUrl;
    }
}
