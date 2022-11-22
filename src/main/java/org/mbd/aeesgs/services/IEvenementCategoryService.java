package org.mbd.aeesgs.services;


import org.mbd.aeesgs.model.EvenementCategory;

public interface IEvenementCategoryService extends IBase<EvenementCategory> {
    public EvenementCategory save(EvenementCategory evenementCategory);
    public EvenementCategory update(EvenementCategory evenementCategory, Long id);
}
