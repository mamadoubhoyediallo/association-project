package org.mbd.aeesgs.controllers;

import lombok.AllArgsConstructor;
import org.mbd.aeesgs.dto.EvenementCategoryDto;
import org.mbd.aeesgs.services.IEvenementCategoryService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("evenementCategory")
@AllArgsConstructor
public class EvenementCategoryController {

    private final IEvenementCategoryService evenementCategoryService;
    
    @PostMapping(value = EndPointAeesgs.SAVE)
    public EvenementCategoryDto save(@Valid @RequestBody EvenementCategoryDto evenementCategoryDto){
        return evenementCategoryService.save(evenementCategoryDto);
    }
    @GetMapping(value = EndPointAeesgs.FIND_ALL)
    public List<EvenementCategoryDto> findAll(){
        return evenementCategoryService.findAll();
    }
    @PutMapping(EndPointAeesgs.UPDATE)
    public EvenementCategoryDto update(@Valid @RequestBody EvenementCategoryDto formationCategoryDto, @PathVariable("id") Long id){
        return evenementCategoryService.update(formationCategoryDto, id);
    }
    @DeleteMapping("deleteById/{id}")
    public void delete(@PathVariable("id") Long id){
        evenementCategoryService.delete(id);
    }
    @GetMapping("findById/{id}")
    public EvenementCategoryDto findyId(@PathVariable("id") Long id){
        return evenementCategoryService.findById(id);
    }
    
}
