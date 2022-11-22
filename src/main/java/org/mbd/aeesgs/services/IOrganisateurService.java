package org.mbd.aeesgs.services;

import org.mbd.aeesgs.model.Organisateur;

import java.util.List;

public interface IOrganisateurService extends IBase<Organisateur> {

    Organisateur findByLibelle(String libelle);
    public Organisateur save(Organisateur organisateur);
    public Organisateur update(Organisateur organisateur, Long id);
}
