package org.mbd.aeesgs.controllers;

import org.mbd.aeesgs.dto.EvenementDto;
import org.mbd.aeesgs.model.Evenement;
import org.mbd.aeesgs.services.IEvenementService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("evenement")
public class EvenementController {

    private final IEvenementService evenementService;

    @Value("${project.images}")
    private String path;

    public EvenementController(IEvenementService evenementService) {
        this.evenementService = evenementService;
    }


    @PostMapping(value = EndPointAeesgs.SAVE)
     public Evenement save(
             @ModelAttribute EvenementDto evenementDto,
             @RequestParam("photo") List<MultipartFile> multipartFile
    ){
        return evenementService.save(evenementDto, multipartFile);
     }

     @GetMapping(value = EndPointAeesgs.FIND_ALL)
     public ResponseEntity<?> findAll(){
         List<Evenement> evenementList = evenementService.findAll();
         return new ResponseEntity<>(evenementList, HttpStatus.OK);
     }
     @PutMapping(value = EndPointAeesgs.UPDATE)
     public Evenement update(
             @PathVariable("id") Long id,
             @ModelAttribute EvenementDto evenementDto,
             @RequestParam("photo") List<MultipartFile> multipartFile
     ){
        return evenementService.update(evenementDto, id, multipartFile);
     }
     @GetMapping(value = EndPointAeesgs.FIND_BY_ID)
     public Evenement evenement(@PathVariable("id") Long id){
        return evenementService.findById(id);
     }
     @DeleteMapping(value = EndPointAeesgs.DELETE)
     public void delete(@PathVariable("id") Long id){
        evenementService.delete(id);
     }
    @GetMapping(value = "/evmnt_photos/{image}", produces = MediaType.IMAGE_PNG_VALUE)
        public void downloadFile(@PathVariable String image, HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        InputStream ressource = evenementService.downloadImage(path, image);
        StreamUtils.copy(ressource, response.getOutputStream());
    }
}
