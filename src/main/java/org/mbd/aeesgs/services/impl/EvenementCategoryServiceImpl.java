package org.mbd.aeesgs.services.impl;

import org.mbd.aeesgs.exception.EntityNotFoundException;
import org.mbd.aeesgs.model.EvenementCategory;
import org.mbd.aeesgs.repositories.EvenementCategoryRepo;
import org.mbd.aeesgs.services.IEvenementCategoryService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class EvenementCategoryServiceImpl implements IEvenementCategoryService {

    private final EvenementCategoryRepo evenementCategoryRepo;
    private final MessageSource messageSource;

    public EvenementCategoryServiceImpl(EvenementCategoryRepo evenementCategoryRepo, MessageSource messageSource) {
        this.evenementCategoryRepo = evenementCategoryRepo;
        this.messageSource = messageSource;
    }

    @Override
    public void delete(Long id) {
        evenementCategoryRepo.deleteById(id);
    }

    @Override
    public List<EvenementCategory> findAll() {
       return evenementCategoryRepo.findAll();
    }

    @Override
    public EvenementCategory findById(Long id) {
        return evenementCategoryRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(messageSource.getMessage(
                        "evenementCategory.notfound", new Object[]{id}, Locale.getDefault()
                )));
    }

    @Override
    public EvenementCategory save(EvenementCategory evenementCategory) {
        return evenementCategoryRepo.save(evenementCategory);
    }

    @Override
    public EvenementCategory update(EvenementCategory evenementCategory, Long id) {
        EvenementCategory evenementCategory1 = evenementCategoryRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(messageSource.getMessage(
                        "evenementCategory.notfound", new Object[]{id}, Locale.getDefault()
                )));
        evenementCategory1.setLibelle(evenementCategory.getLibelle());
        return evenementCategory1;
    }
}
