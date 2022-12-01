package org.mbd.aeesgs.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Evenement extends BaseEntity {
    @Column(name = "libelle", nullable = false, length = 100)
    String libelle;
    @Column(name = "description", nullable = false)
    String description;
    @Column(name = "lieu", nullable = false)
    String lieu;
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    @ManyToMany
    @JoinTable(name = "evenement_images", joinColumns = {
            @JoinColumn(name = "evenement_id")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            })
    Set<ImageUpload> photo;
    @ManyToOne
    @JoinColumn(name = "organisateur_id", nullable = false)
    Organisateur organisateur;
    @ManyToOne
    @JoinColumn(name = "evenementCategory_id", nullable = false)
    EvenementCategory evenementCategory;
}
