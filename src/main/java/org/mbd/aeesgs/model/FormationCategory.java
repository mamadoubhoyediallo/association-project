package org.mbd.aeesgs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormationCategory extends BaseEntity{
    @Column(name = "libelle", nullable = false, length = 100)
    String libelle;
    @OneToMany(mappedBy = "formationCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Formation> formations = new ArrayList<>();;

}
