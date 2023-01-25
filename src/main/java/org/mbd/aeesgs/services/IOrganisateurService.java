package org.mbd.aeesgs.services;

import org.mbd.aeesgs.dto.OrganisateurDto;
import org.mbd.aeesgs.model.Organisateur;

import java.util.List;
import java.util.Optional;

public interface IOrganisateurService extends IBase<OrganisateurDto> {

    OrganisateurDto findByLibelle(String libelle);
    public OrganisateurDto save(OrganisateurDto organisateurDto);
    public OrganisateurDto update(OrganisateurDto organisateurDto, Long id);
}
