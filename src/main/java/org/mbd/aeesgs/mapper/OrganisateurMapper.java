package org.mbd.aeesgs.mapper;


import org.mapstruct.Mapper;
import org.mbd.aeesgs.dto.OrganisateurDto;
import org.mbd.aeesgs.model.Organisateur;

@Mapper
public interface OrganisateurMapper {
    OrganisateurDto toOrganisateurDto(Organisateur organisateur);
    Organisateur fromOrganisateurDto(OrganisateurDto organisateurDto);
     default Organisateur updateOrganisateur(OrganisateurDto organisateurDto, Organisateur organisateur) {
        organisateur.setLibelle(organisateurDto.getLibelle());
        return organisateur;
    }
}
