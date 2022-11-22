package org.mbd.aeesgs.controllers;

import org.mbd.aeesgs.model.EvenementCategory;
import org.mbd.aeesgs.services.IEvenementCategoryService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("evenementCategory")
public class EvenementCategoryController {

    private final IEvenementCategoryService evenementCategoryService;

    public EvenementCategoryController(IEvenementCategoryService evenementCategoryService) {
        this.evenementCategoryService = evenementCategoryService;
    }

    @GetMapping(value = EndPointAeesgs.FIND_ALL)
    public List<EvenementCategory> findAll(){
         return evenementCategoryService.findAll();
    }
    @PostMapping(value = EndPointAeesgs.SAVE)
    public ResponseEntity<?> save(@RequestBody EvenementCategory evenementCategory){
        EvenementCategory fc = evenementCategoryService.save(evenementCategory);
        if (fc == null){
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fc, HttpStatus.OK);
    }
    @PutMapping(value = EndPointAeesgs.UPDATE)
    public EvenementCategory update(@RequestBody EvenementCategory evenementCategory, @PathVariable("id") Long id){
       return evenementCategoryService.update(evenementCategory, id);
    }
    @GetMapping(value = EndPointAeesgs.FIND_BY_ID)
    public EvenementCategory findById(@PathVariable("id") Long id){
        return evenementCategoryService.findById(id);
    }
    @DeleteMapping(value = EndPointAeesgs.DELETE)
    public void delete(@PathVariable("id") Long id){
        evenementCategoryService.delete(id);
    }
}
