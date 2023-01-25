package org.mbd.aeesgs.services;


import org.mbd.aeesgs.dto.EvenementCategoryDto;

public interface IEvenementCategoryService extends IBase<EvenementCategoryDto> {
    public EvenementCategoryDto save(EvenementCategoryDto evenementCategoryDto);
    public EvenementCategoryDto update(EvenementCategoryDto evenementCategoryDto, Long id);
}
