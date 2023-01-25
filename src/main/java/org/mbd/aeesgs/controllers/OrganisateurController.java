package org.mbd.aeesgs.controllers;

import lombok.AllArgsConstructor;
import org.mbd.aeesgs.dto.OrganisateurDto;
import org.mbd.aeesgs.services.IOrganisateurService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("organisateur")
@AllArgsConstructor
public class OrganisateurController {

    private IOrganisateurService organisateurService;

    @PostMapping(value = EndPointAeesgs.SAVE)
    public OrganisateurDto save(@Valid @RequestBody OrganisateurDto organisateurDto){
        return organisateurService.save(organisateurDto);
    }
    @GetMapping(value = EndPointAeesgs.FIND_ALL)
    public List<OrganisateurDto> findAll(){
        return organisateurService.findAll();
    }
    @PutMapping(EndPointAeesgs.UPDATE)
    public OrganisateurDto update(@Valid @RequestBody OrganisateurDto organisateurDto, @PathVariable("id") Long id){
        return organisateurService.update(organisateurDto, id);
    }
    @DeleteMapping("deleteById/{id}")
    public void delete(@PathVariable("id") Long id){
        organisateurService.delete(id);
    }
    @GetMapping("findById/{id}")
    public OrganisateurDto findyId(@PathVariable("id") Long id){
        return organisateurService.findById(id);
    }
}
