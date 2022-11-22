package org.mbd.aeesgs.services.impl;

import org.mbd.aeesgs.exception.EntityNotFoundException;
import org.mbd.aeesgs.model.FormationCategory;
import org.mbd.aeesgs.repositories.FormationCategoryRepo;
import org.mbd.aeesgs.services.IFormationCategoryService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class FormationCategoryServiceImpl implements IFormationCategoryService {

    private FormationCategoryRepo formationCategoryRepo;
    private MessageSource messageSource;

    public FormationCategoryServiceImpl(FormationCategoryRepo formationCategoryRepo, MessageSource messageSource) {
        this.formationCategoryRepo = formationCategoryRepo;
        this.messageSource = messageSource;
    }

    @Override
    public List<FormationCategory> findAll() {
        List<FormationCategory> formationCategories = formationCategoryRepo.findAll();
        if (formationCategories == null) {
            throw new EntityNotFoundException(messageSource.getMessage("formationCategory.notfound", new Object[]{id},
                    Locale.getDefault()));
        }else {
            return formationCategories;
        }
    }

    @Override
    public FormationCategory findById(Long id) {
        return formationCategoryRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(
                        messageSource.getMessage("formationCategory.notfound", new Object[]{id}, Locale.getDefault())
                ));
    }

    @Override
    public FormationCategory findByLibelle(String libelle) {
        return formationCategoryRepo.findByLibelle(libelle);
    }

    @Override
    public FormationCategory save(FormationCategory formationCategory) {
        return formationCategoryRepo.save(formationCategory);
    }

    @Override
    public FormationCategory update(FormationCategory formationCategory, Long id) {
        FormationCategory formationCategory1 = formationCategoryRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(
                        messageSource.getMessage("formationCategory.notfound", new Object[]{id}, Locale.getDefault())
                ));
        formationCategory1.setLibelle(formationCategory.getLibelle());
        return formationCategoryRepo.save(formationCategory1);
    }

    @Override
    public void delete(Long id) {
        formationCategoryRepo.deleteById(id);
    }
}
