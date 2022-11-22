package org.mbd.aeesgs.services;

import org.mbd.aeesgs.dto.FormationDto;
import org.mbd.aeesgs.model.Formation;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface IFormationService extends IBase<Formation> {
    public Formation save(FormationDto formationDto, List<MultipartFile> file);
    public Formation update(FormationDto formationDto, Long id);
    public byte[] downloadImage(String fileName);
    //public File[] affichherImage();
}
