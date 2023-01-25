package org.mbd.aeesgs.services.impl;

import lombok.AllArgsConstructor;
import org.mbd.aeesgs.dto.FormationCategoryDto;
import org.mbd.aeesgs.exception.EntityNotFoundException;
import org.mbd.aeesgs.exception.RequestException;
import org.mbd.aeesgs.mapper.FormationCategoryMapper;
import org.mbd.aeesgs.model.FormationCategory;
import org.mbd.aeesgs.model.Organisateur;
import org.mbd.aeesgs.repositories.FormationCategoryRepo;
import org.mbd.aeesgs.services.IFormationCategoryService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@AllArgsConstructor
public class FormationCategoryServiceImpl implements IFormationCategoryService {

    private FormationCategoryRepo formationCategoryRepo;
    private FormationCategoryMapper formationCategoryMapper;
    private MessageSource messageSource;

    public static final String FORMATION_CATEGORY_NOT_FOUND = "formationCategory.notfound";

    @Override
    public void delete(Long id) {
        try {
            formationCategoryRepo.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("formationCategory.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }

    @Override
    public List<FormationCategoryDto> findAll() {
        return StreamSupport.stream(formationCategoryRepo.findAll().spliterator(), false)
                .map(formationCategoryMapper::toFormationCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public FormationCategoryDto findById(Long id) {
        return formationCategoryMapper.toFormationCategoryDto(formationCategoryRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage(FORMATION_CATEGORY_NOT_FOUND, new Object[]{id},
                        Locale.getDefault()))));
    }

    @Override
    public FormationCategoryDto findByLibelle(String libelle) {
        return null;
    }

    @Override
    public FormationCategoryDto save(FormationCategoryDto formationCategoryDto) {
        formationCategoryRepo.findByLibelle(formationCategoryDto.getLibelle())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage(FORMATION_CATEGORY_NOT_FOUND, new Object[]{formationCategoryDto.getLibelle()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        FormationCategory formationCategory = formationCategoryMapper.fromFormationCategoryDto(formationCategoryDto);
        formationCategory = formationCategoryRepo.save(formationCategory);
        return formationCategoryMapper.toFormationCategoryDto(formationCategory);
    }

    @Override
    public FormationCategoryDto update(FormationCategoryDto formationCategoryDto, Long id) {
        FormationCategory formationCategory = formationCategoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage(FORMATION_CATEGORY_NOT_FOUND, new Object[]{id},
                        Locale.getDefault())));
        formationCategory = formationCategoryMapper.updateFormationCategory(formationCategoryDto, formationCategory);
        formationCategory = formationCategoryRepo.save(formationCategory);
        return formationCategoryMapper.toFormationCategoryDto(formationCategory);
    }
}
