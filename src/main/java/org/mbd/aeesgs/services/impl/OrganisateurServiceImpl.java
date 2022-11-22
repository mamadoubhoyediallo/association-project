package org.mbd.aeesgs.services.impl;

import org.mbd.aeesgs.exception.EntityNotFoundException;
import org.mbd.aeesgs.model.Organisateur;
import org.mbd.aeesgs.repositories.OrganisateurRepo;
import org.mbd.aeesgs.services.IOrganisateurService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class OrganisateurServiceImpl implements IOrganisateurService {

    private OrganisateurRepo organisateurRepo;
    private MessageSource messageSource;

    public OrganisateurServiceImpl(OrganisateurRepo organisateurRepo, MessageSource messageSource) {
        this.organisateurRepo = organisateurRepo;
        this.messageSource = messageSource;
    }

    @Override
    public List<Organisateur> findAll() {
        List<Organisateur> organisateurs = organisateurRepo.findAll();
        if (organisateurs == null){
            throw  new EntityNotFoundException(messageSource.getMessage("organisateur.notfound", new Object[]{id},
                    Locale.getDefault()));
        }else {
            return organisateurs;
        }
    }

    @Override
    public Organisateur findById(Long id) {
        Organisateur organisateur = organisateurRepo.findById(id).
                orElseThrow(()-> new EntityNotFoundException(messageSource.getMessage("organisateur.notfound", new Object[]{id},
                        Locale.getDefault())));
        return organisateur;
    }
    @Override
    public Organisateur findByLibelle(String libelle) {
        return organisateurRepo.findByLibelle(libelle);
    }

    @Override
    public Organisateur save(Organisateur organisateur) {
        return organisateurRepo.save(organisateur);
    }

    @Override
    public Organisateur update(Organisateur organisateur, Long id) {
        Organisateur organisateurs = organisateurRepo.findById(id).get();
        organisateurs.setLibelle(organisateur.getLibelle());
        return organisateurRepo.save(organisateurs);
    }

    @Override
    public void delete(Long id) {
        organisateurRepo.deleteById(id);
    }
}
