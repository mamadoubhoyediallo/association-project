package org.mbd.aeesgs.controllers;

import org.mbd.aeesgs.model.Organisateur;
import org.mbd.aeesgs.services.IOrganisateurService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organisateur")
public class OrganisateurController {

    private IOrganisateurService organisateurService;

    public OrganisateurController(IOrganisateurService organisateurService) {
        this.organisateurService = organisateurService;
    }
    @PostMapping(value = EndPointAeesgs.SAVE)
    public Organisateur save(@RequestBody Organisateur organisateur){
        return organisateurService.save(organisateur);
    }
    @GetMapping(value = EndPointAeesgs.FIND_ALL)
    public List<Organisateur> findAll(){
        List<Organisateur> list = organisateurService.findAll();
        return list;
    }
    @PutMapping(value = EndPointAeesgs.UPDATE)
    public Organisateur update(@RequestBody Organisateur organisateur, @PathVariable("id") Long id){
        return organisateurService.update(organisateur, id);
    }
    @GetMapping(EndPointAeesgs.FIND_BY_ID)
    public Organisateur findById(@PathVariable("id") Long id){
        return organisateurService.findById(id);
    }
    @DeleteMapping("deleteById/{id}")
    public void delete(@PathVariable("id") Long id){
        organisateurService.delete(id);
    }
}
