package org.mbd.aeesgs.services.impl;

import lombok.AllArgsConstructor;
import org.mbd.aeesgs.dto.OrganisateurDto;
import org.mbd.aeesgs.exception.EntityNotFoundException;
import org.mbd.aeesgs.exception.RequestException;
import org.mbd.aeesgs.mapper.OrganisateurMapper;
import org.mbd.aeesgs.model.Organisateur;
import org.mbd.aeesgs.repositories.OrganisateurRepo;
import org.mbd.aeesgs.services.IOrganisateurService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@CacheConfig(cacheNames = "organisateurs")
@AllArgsConstructor
public class OrganisateurServiceImpl implements IOrganisateurService {

    private OrganisateurRepo organisateurRepo;
    private MessageSource messageSource;
    private OrganisateurMapper organisateurMapper;
    public static final String ORGANISATEUR_NOT_FOUND = "organisateur.notfound";

    @Override
    public List<OrganisateurDto> findAll() {
        return StreamSupport.stream(organisateurRepo.findAll().spliterator(), false)
                .map(organisateurMapper::toOrganisateurDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrganisateurDto findById(Long id) {
        return organisateurMapper.toOrganisateurDto(organisateurRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage(ORGANISATEUR_NOT_FOUND, new Object[]{id},
                        Locale.getDefault()))));
    }
    @Override
    public OrganisateurDto findByLibelle(String libelle) {
        return organisateurMapper.toOrganisateurDto(organisateurRepo.findByLibelle(libelle).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage(ORGANISATEUR_NOT_FOUND, new Object[]{libelle},
                        Locale.getDefault()))));
    }

    @Override
    public OrganisateurDto save(OrganisateurDto organisateurDto) {
        organisateurRepo.findByLibelle(organisateurDto.getLibelle())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("organisateur.exists", new Object[]{organisateurDto.getLibelle()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        Organisateur organisateur = organisateurMapper.fromOrganisateurDto(organisateurDto);
        organisateur = organisateurRepo.save(organisateur);
        return organisateurMapper.toOrganisateurDto(organisateur);

    }

    @Transactional
    public OrganisateurDto update(OrganisateurDto organisateurDto, Long id) {
         Organisateur organisateur = organisateurRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage(ORGANISATEUR_NOT_FOUND, new Object[]{id},
                        Locale.getDefault())));
         organisateur = organisateurMapper.updateOrganisateur(organisateurDto, organisateur);
         organisateur = organisateurRepo.save(organisateur);
         return organisateurMapper.toOrganisateurDto(organisateur);
    }

    @Override
    public void delete(Long id) {
        try {
            organisateurRepo.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("organisateur.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
