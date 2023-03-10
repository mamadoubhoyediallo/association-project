package org.mbd.aeesgs.services;


import org.mbd.aeesgs.dto.FormationCategoryDto;
import org.mbd.aeesgs.model.FormationCategory;

import java.util.List;

public interface IFormationCategoryService extends IBase<FormationCategoryDto> {
    FormationCategoryDto findByLibelle(String libelle);
    public FormationCategoryDto save(FormationCategoryDto formationCategoryDto);
    public FormationCategoryDto update(FormationCategoryDto formationCategoryDto, Long id);

}
