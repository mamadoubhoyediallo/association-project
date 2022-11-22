package org.mbd.aeesgs.services.impl;

import org.mbd.aeesgs.dto.FormationDto;
import org.mbd.aeesgs.model.Formation;
import org.mbd.aeesgs.model.FormationCategory;
import org.mbd.aeesgs.model.Organisateur;
import org.mbd.aeesgs.repositories.FormationRepo;
import org.mbd.aeesgs.services.IFormationCategoryService;
import org.mbd.aeesgs.services.IFormationService;
import org.mbd.aeesgs.services.IOrganisateurService;
import org.mbd.aeesgs.utils.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FormationServiceImpl implements IFormationService {
    private final FormationRepo formationRepo;
    private final IOrganisateurService organisateurService;
    private final IFormationCategoryService formationCategoryService;

    public FormationServiceImpl(FormationRepo formationRepo, IOrganisateurService organisateurService, IFormationCategoryService formationCategoryService) {
        this.formationRepo = formationRepo;
        this.organisateurService = organisateurService;
        this.formationCategoryService = formationCategoryService;
    }

    @Override
    public Formation save(FormationDto formationDto, List<MultipartFile> file) {
        String imgname="";
        for(int i=0;i<file.size();i++) {
            String fileName = StringUtils.cleanPath(file.get(i).getOriginalFilename());
            if(i==0) {
                imgname=fileName;
            }
            else {
                imgname=imgname +","+ fileName;
            }

        }
        Organisateur organisateur = organisateurService.findById(formationDto.getId_organisateur());
        FormationCategory formationCategory = formationCategoryService.findById(formationDto.getId_formationCategory());
        Formation formation = new Formation();
        formation.setLibelle(formationDto.getLibelle());
        formation.setDescription(formationDto.getDescription());
        formation.setLieu(formationDto.getLieu());
        formation.setPhoto(imgname);
        formation.setDate(formationDto.getDate());
        formation.setPrix(formationDto.getPrix());
        formation.setOrganisateur(organisateur);
        formation.setFormationCategory(formationCategory);
        formation.setPhoto(imgname);
        Formation formation1 = formationRepo.save(formation);
        String uploadDir = "dept_photos/";
        for(int i=0;i<file.size();i++) {
            String fileName = StringUtils.cleanPath(file.get(i).getOriginalFilename());
            try {
                FileUploadUtil.saveFile(uploadDir, fileName, file.get(i));
                System.out.println(uploadDir + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    public byte[] downloadImage(String fileName){
        Optional<Formation> dbImageData = formationRepo.findByPhoto(fileName);
        byte[] images=FileUploadUtil.decompressImage(dbImageData.get().getPhoto());
        return images;
    }

//    @Override
//    public File[] affichherImage() {
//        File nomcomplet = new File("/Users/mamadoubhoyediallo/Documents/aeesgs/aeesgs-back-office/aeesgs/dept_photos/2");
//        File[] contennt = nomcomplet.listFiles();
//        return contennt;
//    }

}
