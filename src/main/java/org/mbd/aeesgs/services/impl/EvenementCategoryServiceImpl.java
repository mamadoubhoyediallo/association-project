package org.mbd.aeesgs.services.impl;

import lombok.AllArgsConstructor;
import org.mbd.aeesgs.dto.EvenementCategoryDto;
import org.mbd.aeesgs.exception.EntityNotFoundException;
import org.mbd.aeesgs.exception.RequestException;
import org.mbd.aeesgs.mapper.EvenementCategoryMapper;
import org.mbd.aeesgs.model.EvenementCategory;
import org.mbd.aeesgs.repositories.EvenementCategoryRepo;
import org.mbd.aeesgs.services.IEvenementCategoryService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EvenementCategoryServiceImpl implements IEvenementCategoryService {

    private final EvenementCategoryRepo evenementCategoryRepo;
    private final MessageSource messageSource;
    private final EvenementCategoryMapper evenementCategoryMapper;

    public static final String EVENEMENT_CATEGORY_NOT_FOUND = "evenementCategory.notfound";

    @Override
    public void delete(Long id) {
        try {
            evenementCategoryRepo.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("evenementCategory.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }

    @Override
    public List<EvenementCategoryDto> findAll() {
        return StreamSupport.stream(evenementCategoryRepo.findAll().spliterator(), false)
                .map(evenementCategoryMapper::toEvenementCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public EvenementCategoryDto findById(Long id) {
        return evenementCategoryMapper.toEvenementCategoryDto(evenementCategoryRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage(EVENEMENT_CATEGORY_NOT_FOUND, new Object[]{id},
                        Locale.getDefault()))));
    }

    @Override
    public EvenementCategoryDto save(EvenementCategoryDto evenementCategoryDto) {
        evenementCategoryRepo.findByLibelle(evenementCategoryDto.getLibelle())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("evenementCategory.exists", new Object[]{evenementCategoryDto.getLibelle()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        EvenementCategory evenementCategory = evenementCategoryMapper.fromEvenementCategoryDto(evenementCategoryDto);
        evenementCategory = evenementCategoryRepo.save(evenementCategory);
        return evenementCategoryMapper.toEvenementCategoryDto(evenementCategory);
    }

    @Override
    public EvenementCategoryDto update(EvenementCategoryDto evenementCategoryDto, Long id) {
        EvenementCategory evenementCategory = evenementCategoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage(EVENEMENT_CATEGORY_NOT_FOUND, new Object[]{id},
                        Locale.getDefault())));
        evenementCategory = evenementCategoryMapper.updateEvenementCategory(evenementCategoryDto, evenementCategory);
        evenementCategory = evenementCategoryRepo.save(evenementCategory);
        return evenementCategoryMapper.toEvenementCategoryDto(evenementCategory);
    }
}
