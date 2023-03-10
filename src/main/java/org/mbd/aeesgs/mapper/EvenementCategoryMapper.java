package org.mbd.aeesgs.mapper;

import org.mapstruct.Mapper;
import org.mbd.aeesgs.dto.EvenementCategoryDto;
import org.mbd.aeesgs.model.EvenementCategory;
import org.mbd.aeesgs.model.EvenementCategory;

@Mapper
public interface EvenementCategoryMapper {
    EvenementCategoryDto toEvenementCategoryDto(EvenementCategory evenementCategory);
    EvenementCategory fromEvenementCategoryDto(EvenementCategoryDto formationCategoryDto);
    default EvenementCategory updateEvenementCategory(EvenementCategoryDto evenementCategoryDto, EvenementCategory evenementCategory) {
        evenementCategory.setLibelle(evenementCategoryDto.getLibelle());
        return evenementCategory;
    }
}
