package org.mbd.aeesgs.services;

import org.mbd.aeesgs.dto.EvenementDto;
import org.mbd.aeesgs.dto.FormationDto;
import org.mbd.aeesgs.model.Evenement;
import org.mbd.aeesgs.model.Formation;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface IEvenementService extends IBase<Evenement> {
    public Evenement save(EvenementDto evenementDto, List<MultipartFile> files);
    public Evenement update(EvenementDto evenementDto, Long id, List<MultipartFile> files);
    public InputStream downloadImage(String path, String fileName) throws Exception;
}
