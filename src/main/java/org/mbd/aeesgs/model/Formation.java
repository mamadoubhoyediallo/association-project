package org.mbd.aeesgs.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Formation extends BaseEntity {
    Double prix;
    @Column(name = "libelle", nullable = false, length = 100)
    String libelle;
    @Column(name = "description", nullable = false)
    String description;
    @Column(name = "lieu", nullable = false)
    String lieu;
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    @ManyToMany
            @JoinTable(name = "formation_images", joinColumns = {
                    @JoinColumn(name = "formation_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            })
    Set<ImageUpload> photo;
    @ManyToOne
    @JoinColumn(name = "organisateur_id", nullable = false)
    Organisateur organisateur;
    @ManyToOne
    @JoinColumn(name = "formationCategory_id", nullable = false)
    FormationCategory formationCategory;
}
