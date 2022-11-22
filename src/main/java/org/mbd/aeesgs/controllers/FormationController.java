package org.mbd.aeesgs.controllers;

import org.mbd.aeesgs.dto.FormationDto;
import org.mbd.aeesgs.dto.FormationResponseDto;
import org.mbd.aeesgs.model.Formation;
import org.mbd.aeesgs.services.IFormationService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("formation")
public class FormationController {

    private final IFormationService formationService;

    public FormationController(IFormationService formationService) {
        this.formationService = formationService;
    }
    @PostMapping(value = EndPointAeesgs.SAVE)
     public FormationResponseDto save(@ModelAttribute FormationDto formationDto, @RequestParam("photo") List<MultipartFile> multipartFile){
        Formation formation = formationService.save(formationDto, multipartFile);
        String downloadURl = "";
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dept_photos/")
                .path(formation.getPhoto())
                .toUriString();
        System.out.println(downloadURl);
        return new FormationResponseDto(formation.getPhoto(),
                downloadURl);

     }
     @GetMapping(value = EndPointAeesgs.FIND_ALL)
     public ResponseEntity<?> findAll(){
         List<Formation> formationList = formationService.findAll();
         return new ResponseEntity<>(formationList, HttpStatus.OK);
     }
     @PutMapping(value = EndPointAeesgs.UPDATE)
     public Formation update(@PathVariable("id") Long id, @RequestBody FormationDto formationDto){
        return formationService.update(formationDto, id);
     }
     @GetMapping(value = EndPointAeesgs.FIND_BY_ID)
     public Formation formation(@PathVariable("id") Long id){
        return formationService.findById(id);
     }
     @DeleteMapping(value = EndPointAeesgs.DELETE)
     public void delete(@PathVariable("id") Long id){
        formationService.delete(id);
     }

    @GetMapping(value = "getImage/{id}")
    public ResponseEntity<?> download(@PathVariable("id") String photo){
        return new ResponseEntity<>(formationService.downloadImage(photo), HttpStatus.OK);
    }
//    @GetMapping(value = "affImage")
//    public ResponseEntity<?> afficheImage(){
//        return new ResponseEntity<>(formationService.affichherImage(), HttpStatus.OK);
//    }
}
