package org.mbd.aeesgs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    private Long id;
    @JsonIgnore
    private Date createdAt;
    @JsonIgnore
    private Date updatedAt;
}
