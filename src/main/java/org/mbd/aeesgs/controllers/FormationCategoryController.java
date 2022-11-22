package org.mbd.aeesgs.controllers;

import org.mbd.aeesgs.model.FormationCategory;
import org.mbd.aeesgs.services.IFormationCategoryService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("formationCategory")
public class FormationCategoryController {

    private IFormationCategoryService formationCategoryService;

    public FormationCategoryController(IFormationCategoryService formationCategoryService) {
        this.formationCategoryService = formationCategoryService;
    }

    @GetMapping(value = EndPointAeesgs.FIND_ALL)
    public List<FormationCategory> findAll(){
        List<FormationCategory> formationCategories = formationCategoryService.findAll();
        return formationCategories;
    }
    @PostMapping(value = EndPointAeesgs.SAVE)
    public ResponseEntity<?> save(@RequestBody FormationCategory formationCategory){
        FormationCategory fc = formationCategoryService.save(formationCategory);
        if (fc == null){
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fc, HttpStatus.OK);
    }
    @PutMapping(value = EndPointAeesgs.UPDATE)
    public FormationCategory update(@RequestBody FormationCategory formationCategory, @PathVariable("id") Long id){
       return formationCategoryService.update(formationCategory, id);
    }
    @GetMapping(value = EndPointAeesgs.FIND_BY_ID)
    public FormationCategory findById(@PathVariable("id") Long id){
        return formationCategoryService.findById(id);
    }
    @DeleteMapping(value = EndPointAeesgs.DELETE)
    public void delete(@PathVariable("id") Long id){
        formationCategoryService.delete(id);
    }
}
