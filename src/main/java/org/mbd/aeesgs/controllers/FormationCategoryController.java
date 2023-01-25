package org.mbd.aeesgs.controllers;

import lombok.AllArgsConstructor;
import org.mbd.aeesgs.dto.FormationCategoryDto;
import org.mbd.aeesgs.services.IFormationCategoryService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("formationCategory")
@AllArgsConstructor
public class FormationCategoryController {

    private IFormationCategoryService formationCategoryService;

    @PostMapping(value = EndPointAeesgs.SAVE)
    public FormationCategoryDto save(@Valid @RequestBody FormationCategoryDto formationCategoryDto){
        return formationCategoryService.save(formationCategoryDto);
    }
    @GetMapping(value = EndPointAeesgs.FIND_ALL)
    public List<FormationCategoryDto> findAll(){
        return formationCategoryService.findAll();
    }
    @PutMapping(EndPointAeesgs.UPDATE)
    public FormationCategoryDto update(@Valid @RequestBody FormationCategoryDto formationCategoryDto, @PathVariable("id") Long id){
        return formationCategoryService.update(formationCategoryDto, id);
    }
    @DeleteMapping("deleteById/{id}")
    public void delete(@PathVariable("id") Long id){
        formationCategoryService.delete(id);
    }
    @GetMapping("findById/{id}")
    public FormationCategoryDto findyId(@PathVariable("id") Long id){
        return formationCategoryService.findById(id);
    }
}
