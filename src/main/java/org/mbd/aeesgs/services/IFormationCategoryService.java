package org.mbd.aeesgs.services;


import org.mbd.aeesgs.model.FormationCategory;

import java.util.List;

public interface IFormationCategoryService extends IBase<FormationCategory> {
    FormationCategory findByLibelle(String libelle);
    public FormationCategory save(FormationCategory formationCategory);
    public FormationCategory update(FormationCategory formationCategory, Long id);

}
