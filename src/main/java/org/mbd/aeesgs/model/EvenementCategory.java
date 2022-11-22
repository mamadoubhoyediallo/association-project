package org.mbd.aeesgs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class EvenementCategory extends BaseEntity{
    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;
}
