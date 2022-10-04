package com.cg.model.dto;

import com.cg.model.RegionLocation;
import com.cg.model.Role;
import com.cg.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserUpdateDTO {

    private Long id;

    private String imageUrl;

    @NotBlank(message = "Username is required!")
    @Pattern(regexp = "^(([\\w\\d._]*)+(@[\\w]{2,})+(.[\\w]{2,3})||([\\w\\d._]*)+(@[\\w]{2,})+(.[\\w]{2,3})+(.[\\w]{2}))$",
            message = "Username must be an email!")
    @Size(min = 6, message = "Username contains 8 characters minimum!")
    @Size(max = 30, message = "Username contains 30 characters maximum!")
    private String username;

    @NotBlank(message = "Name is required!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name contains characters only!")
    @Size(min = 2, message = "Name contains 2 characters minimum!")
    @Size(max = 50, message = "Name contains 50 characters maximum!")
    private String fullName;

    @NotBlank(message = "Phone numbers is required!")
    @Pattern(regexp = "^((0?)||84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$",
                message = "Phone is not valid!")
    private String phone;

    @Valid
    private RegionLocationDTO regionLocation;

    @Valid
    private RoleDTO role;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;

    public UserUpdateDTO(Long id, String imageUrl, String username, String fullName, String phone, RegionLocation regionLocation, Role role) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.regionLocation = regionLocation.toRegionLocationDTO();
        this.role = role.toRoleDTO();
    }


    public User toUser() {
        return new User()
                .setId(id)
                .setImageUrl(imageUrl)
                .setUsername(username)
                .setFullName(fullName)
                .setPhone(phone)
                .setRegionLocation(regionLocation.toRegionLocation())
                .setRole(role.toRole());
    }
}

