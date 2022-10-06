package com.cg.model.dto;

import com.cg.model.Role;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class RoleDTO {

    private Long id;
    private String roleName;

    public Role toRole() {
        return new Role()
                .setId(id)
                .setRoleName(roleName);
    }
}
