package org.mbd.aeesgs.services;

import org.mbd.aeesgs.dto.FormationDto;
import org.mbd.aeesgs.model.Formation;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface IFormationService extends IBase<Formation> {
    public Formation save(FormationDto formationDto, List<MultipartFile> files);
    public Formation update(FormationDto formationDto, Long id, List<MultipartFile> files);
    public InputStream downloadImage(String path, String fileName) throws Exception;
    //public File[] affichherImage();
}
