package org.mbd.aeesgs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Organisateur extends BaseEntity {
    @Column(name = "libelle", nullable = false, length = 100, unique = true)
    @NotNull
    private String libelle;
    @OneToMany(mappedBy = "organisateur", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Formation> formations = new ArrayList<>();
    @OneToMany(mappedBy = "organisateur", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Evenement> evenements = new ArrayList<>();
}
