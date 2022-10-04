package com.cg.model;

import com.cg.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @Column(nullable = false, updatable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private String phone;

    @OneToOne
    @JoinColumn(name ="region_location_id" )
    private RegionLocation regionLocation;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    public UserDTO toUserDTO(){
        return new UserDTO()
                .setId(id)
                .setImageUrl(imageUrl)
                .setUsername(username)
                .setPassword(password)
                .setFullName(fullName)
                .setPhone(phone)
                .setRegionLocation(regionLocation.toRegionLocationDTO())
                .setRole(role.toRoleDTO());
    }
}
