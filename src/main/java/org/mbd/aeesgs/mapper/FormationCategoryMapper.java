package org.mbd.aeesgs.mapper;

import org.mapstruct.Mapper;
import org.mbd.aeesgs.dto.FormationCategoryDto;
import org.mbd.aeesgs.dto.OrganisateurDto;
import org.mbd.aeesgs.model.FormationCategory;
import org.mbd.aeesgs.model.Organisateur;

@Mapper
public interface FormationCategoryMapper{
    FormationCategoryDto toFormationCategoryDto(FormationCategory formationCategory);
    FormationCategory fromFormationCategoryDto(FormationCategoryDto formationCategoryDto);
    default FormationCategory updateFormationCategory(FormationCategoryDto formationCategoryDto, FormationCategory formationCategory) {
        formationCategory.setLibelle(formationCategoryDto.getLibelle());
        return formationCategory;
    }
}
