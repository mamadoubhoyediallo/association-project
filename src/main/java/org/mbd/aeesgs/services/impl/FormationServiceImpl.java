package org.mbd.aeesgs.services.impl;

import org.mbd.aeesgs.dto.FormationDto;
import org.mbd.aeesgs.model.Formation;
import org.mbd.aeesgs.model.FormationCategory;
import org.mbd.aeesgs.model.ImageUpload;
import org.mbd.aeesgs.model.Organisateur;
import org.mbd.aeesgs.repositories.FormationRepo;
import org.mbd.aeesgs.repositories.ImageUploadRepo;
import org.mbd.aeesgs.services.IFormationCategoryService;
import org.mbd.aeesgs.services.IFormationService;
import org.mbd.aeesgs.services.IOrganisateurService;
import org.mbd.aeesgs.utils.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class FormationServiceImpl implements IFormationService {
    private final FormationRepo formationRepo;
    private final IOrganisateurService organisateurService;
    private final IFormationCategoryService formationCategoryService;
    private final ImageUploadRepo imageUploadRepo;
    String downloadURl = "";
    public FormationServiceImpl(FormationRepo formationRepo, IOrganisateurService organisateurService, IFormationCategoryService formationCategoryService, ImageUploadRepo imageUploadRepo) {
        this.formationRepo = formationRepo;
        this.organisateurService = organisateurService;
        this.formationCategoryService = formationCategoryService;
        this.imageUploadRepo = imageUploadRepo;
    }
    @Override
    public Formation save(FormationDto formationDto, List<MultipartFile> files) {
        Set<ImageUpload> imageUploads = new HashSet<>();
        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("formation/dept_photos/")
                    .path(fileName)
                    .toUriString();
            try {
                ImageUpload image = new ImageUpload(
                        downloadURl,
                        file.getContentType()
                );
                imageUploadRepo.save(image);
                imageUploads.add(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Organisateur organisateur = organisateurService.findById(formationDto.getId_organisateur());
        FormationCategory formationCategory = formationCategoryService.findById(formationDto.getId_formationCategory());
        Formation formation = new Formation();
        formation.setLibelle(formationDto.getLibelle());
        formation.setDescription(formationDto.getDescription());
        formation.setLieu(formationDto.getLieu());
        //formation.setPhoto(imgname);
        formation.setDate(formationDto.getDate());
        formation.setPrix(formationDto.getPrix());
        formation.setOrganisateur(organisateur);
        formation.setFormationCategory(formationCategory);
        formation.setPhoto(imageUploads);
        Formation formation1 = formationRepo.save(formation);
        String uploadDir = "dept_photos/";
        files.forEach(file -> {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                FileUploadUtil.saveFile(uploadDir, fileName, file);
                System.out.println(uploadDir + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return formation1;
    }


    @Override
    public Formation update(FormationDto formationDto, Long id) {
        Formation formations = formationRepo.findById(id).orElse(null);
        Organisateur organisateur = organisateurService.findById(formationDto.getId_organisateur());
        FormationCategory formationCategory = formationCategoryService.findById(formationDto.getId_formationCategory());
        formations.setLibelle(formationDto.getLibelle());
        formations.setDescription(formationDto.getDescription());
        formations.setLieu(formationDto.getLieu());
        formations.setDate(formationDto.getDate());
        //formations.setPhoto(ima);
        formations.setPrix(formationDto.getPrix());
        formations.setOrganisateur(organisateur);
        formations.setFormationCategory(formationCategory);
        return formationRepo.save(formations);
    }

    @Override
    public List<Formation> findAll() {
        return formationRepo.findAll();
    }

    @Override
    public Formation findById(Long id) {
        return formationRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        formationRepo.deleteById(id);
    }

    @Override
    public InputStream downloadImage(String path, String fileName) throws Exception{
        String fullPath = path+File.separator+fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }

//    @Override
//    public File[] affichherImage() {
//        File nomcomplet = new File("/Users/mamadoubhoyediallo/Documents/aeesgs/aeesgs-back-office/aeesgs/dept_photos/2");
//        File[] contennt = nomcomplet.listFiles();
//        return contennt;
//    }

}
