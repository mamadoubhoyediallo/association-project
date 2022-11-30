package org.mbd.aeesgs.controllers;

import org.mbd.aeesgs.dto.FormationDto;
import org.mbd.aeesgs.dto.FormationResponseDto;
import org.mbd.aeesgs.model.Formation;
import org.mbd.aeesgs.repositories.FormationRepo;
import org.mbd.aeesgs.services.IFormationService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("formation")
public class FormationController {

    private final IFormationService formationService;
    private final FormationRepo formationRepo;
    @Value("${project.image}")
    private String path;

    public FormationController(IFormationService formationService, FormationRepo formationRepo) {
        this.formationService = formationService;
        this.formationRepo = formationRepo;
    }

    @PostMapping(value = EndPointAeesgs.SAVE)
     public Formation save(@ModelAttribute FormationDto formationDto, @RequestParam("photo") List<MultipartFile> multipartFile){
        Formation formation = formationService.save(formationDto, multipartFile);
        return formation;
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
    @GetMapping(value = "/dept_photos/{image}", produces = MediaType.IMAGE_PNG_VALUE)
        public void downloadFile(@PathVariable String image, HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        InputStream ressource = formationService.downloadImage(path, image);
        StreamUtils.copy(ressource, response.getOutputStream());
    }
}
