package org.mbd.aeesgs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUpload extends BaseEntity{
    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;
    private String type;
}
