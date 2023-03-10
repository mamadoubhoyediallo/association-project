package org.mbd.aeesgs.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mbd.aeesgs.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser extends BaseEntity {
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> appRoles = new ArrayList<AppRole>();

}
