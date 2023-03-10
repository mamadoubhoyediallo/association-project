package org.mbd.aeesgs.services.impl;

import org.mbd.aeesgs.dto.EvenementDto;
import org.mbd.aeesgs.dto.FormationDto;
import org.mbd.aeesgs.exception.EntityNotFoundException;
import org.mbd.aeesgs.model.*;
import org.mbd.aeesgs.repositories.EvenementRepo;
import org.mbd.aeesgs.repositories.ImageUploadRepo;
import org.mbd.aeesgs.services.IEvenementCategoryService;
import org.mbd.aeesgs.services.IEvenementService;
import org.mbd.aeesgs.services.IOrganisateurService;
import org.mbd.aeesgs.utils.FileUploadUtil;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class EvenementServiceImpl implements IEvenementService {
    private final EvenementRepo evenementRepo;
    private final IOrganisateurService organisateurService;
    private final IEvenementCategoryService evenementCategoryService;
    private final ImageUploadRepo imageUploadRepo;
    private MessageSource messageSource;
    String downloadURl = "";

    public EvenementServiceImpl(EvenementRepo evenementRepo, IOrganisateurService organisateurService, IEvenementCategoryService evenementCategoryService, ImageUploadRepo imageUploadRepo, MessageSource messageSource) {
        this.evenementRepo = evenementRepo;
        this.organisateurService = organisateurService;
        this.evenementCategoryService = evenementCategoryService;
        this.imageUploadRepo = imageUploadRepo;
        this.messageSource = messageSource;
    }
    @Override
    public Evenement save(EvenementDto evenementDto, List<MultipartFile> files) {
//        Set<ImageUpload> imageUploads = new HashSet<>();
//        files.forEach(file -> {
//            String fileName = file.getOriginalFilename();
//            downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("formation/dept_photos/")
//                    .path(fileName)
//                    .toUriString();
//            try {
//                ImageUpload image = new ImageUpload(
//                        downloadURl,
//                        file.getContentType()
//                );
//                imageUploadRepo.save(image);
//                imageUploads.add(image);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        Organisateur organisateur = organisateurService.findById(evenementDto.getId_organisateur());
//        EvenementCategory evenementCategory = evenementCategoryService.findById(evenementDto.getId_formationCategory());
//        Evenement evenement = new Evenement();
//        evenement.setLibelle(evenementDto.getLibelle());
//        evenement.setDescription(evenementDto.getDescription());
//        evenement.setLieu(evenementDto.getLieu());
//        evenement.setDate(evenementDto.getDate());
//        evenement.setOrganisateur(organisateur);
//        evenement.setEvenementCategory(evenementCategory);
//        evenement.setPhoto(imageUploads);
//        Evenement evenement1 = evenementRepo.save(evenement);
//        String uploadDir = "evnnmt_photos/";
//        files.forEach(file -> {
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            try {
//                FileUploadUtil.saveFile(uploadDir, fileName, file);
//                System.out.println(uploadDir + fileName);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        return evenement1;
        return null;
    }

    @Override
    public Evenement update(EvenementDto evenementDto, Long id, List<MultipartFile> files) {
//        Evenement evenements = evenementRepo.findById(id).orElseThrow(
//                ()-> new EntityNotFoundException(messageSource.getMessage("formation.notfound", new Object[]{id},
//                        Locale.getDefault()))
//        );
//        Set<ImageUpload> imageUploads = new HashSet<>();
//        files.forEach(file -> {
//            String fileName = file.getOriginalFilename();
//            downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("evenement/dept_photos/")
//                    .path(fileName)
//                    .toUriString();
//            try {
//                ImageUpload image = new ImageUpload(
//                        downloadURl,
//                        file.getContentType()
//                );
//                imageUploadRepo.save(image);
//                imageUploads.add(image);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        Organisateur organisateur = organisateurService.findById(evenementDto.getId_organisateur());
//        EvenementCategory evenementCategory = evenementCategoryService.findById(evenementDto.getId_formationCategory());
//        evenements.setLibelle(evenementDto.getLibelle());
//        evenements.setDescription(evenementDto.getDescription());
//        evenements.setLieu(evenementDto.getLieu());
//        evenements.setDate(evenementDto.getDate());
//        evenements.setOrganisateur(organisateur);
//        evenements.setEvenementCategory(evenementCategory);
//        evenements.setPhoto(imageUploads);
//        String uploadDir = "evemnt_photos/";
//        files.forEach(file -> {
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            try {
//                FileUploadUtil.saveFile(uploadDir, fileName, file);
//                System.out.println(uploadDir + fileName);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        return evenementRepo.save(evenements);
        return null;
    }

    @Override
    public List<Evenement> findAll() {
        return evenementRepo.findAll();
    }

    @Override
    public Evenement findById(Long id) {
        return evenementRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        evenementRepo.deleteById(id);
    }

    @Override
    public InputStream downloadImage(String path, String fileName) throws Exception{
        String fullPath = path+File.separator+fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }


}
