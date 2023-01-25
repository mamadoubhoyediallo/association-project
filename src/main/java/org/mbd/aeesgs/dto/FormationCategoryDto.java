package org.mbd.aeesgs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormationCategoryDto extends BaseDto{
    @NotNull(message = "libelle is required")
    private String libelle;
}
