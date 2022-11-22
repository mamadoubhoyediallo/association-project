package org.mbd.aeesgs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Evenement extends BaseEntity {
    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "lieu", nullable = false)
    private String lieu;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
