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
     public FormationResponseDto save(@ModelAttribute FormationDto formationDto, @RequestParam("photo") List<MultipartFile> multipartFile){

        Formation formation = formationService.save(formationDto, multipartFile);
        String downloadURl = "";
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("formation/dept_photos/")
                .path(formation.getPhoto())
                .toUriString();
        System.out.println(downloadURl);
        return new FormationResponseDto(formation.getPhoto(),
                downloadURl);

     }

//     public Set<ImageUpload> uploads(MultipartFile[] files) throws IOException {
//         Set<ImageUpload> imageUploads = new HashSet<>();
//         for (MultipartFile file: files){
//             ImageUpload upload = new ImageUpload(
//                     file.getOriginalFilename(),
//                     file.getContentType(),
//                     file.getBytes()
//             );
//             imageUploads.add(imageUploads);
//         }
//
//     }

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

//    @GetMapping(value = "getImage/{id}")
//    public ResponseEntity<?> download(@PathVariable("id") String photo){
//        return new ResponseEntity<>(formationService.downloadImage(photo), HttpStatus.OK);
//    }
//    @GetMapping(value = "affImage")
//    public ResponseEntity<?> afficheImage(){
//        return new ResponseEntity<>(formationService.affichherImage(), HttpStatus.OK);
//    }


//    @GetMapping("/dept_photos/{image}")
//    public ResponseEntity<InputStreamSource> downloadFile(@PathVariable String image) throws Exception {
//        Optional<Formation> formation = null;
//        //byte[] bytes = new byte[10];
//        formation = formationRepo.findByPhoto(image);
//        File file = new File(formation.get().getPhoto());
//        InputStreamSource streamSource = new InputStreamResource(new FileInputStream(file));
//        return  ResponseEntity.ok()
//                .contentType(MediaType.ALL)
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "formation; filename=\"" + formation.get().getPhoto()
//                                + "\"")
//                .body(streamSource);
//    }
    @GetMapping(value = "/dept_photos/{image}", produces = MediaType.IMAGE_PNG_VALUE)
        public void downloadFile(@PathVariable String image, HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        InputStream ressource = formationService.downloadImage(path, image);
        StreamUtils.copy(ressource, response.getOutputStream());
    }
}
