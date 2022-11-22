package org.mbd.aeesgs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
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
    String photo;
    @ManyToOne
    @JoinColumn(name = "organisateur_id", nullable = false)
    Organisateur organisateur;
    @ManyToOne
    @JoinColumn(name = "formationCategory_id", nullable = false)
    FormationCategory formationCategory;
}
