package com.cg.repository;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByPhone(String phone);

    @Query("SELECT NEW com.cg.model.dto.UserDTO(" +
            "u.id, " +
            "u.imageUrl, " +
            "u.username, " +
            "u.fullName, " +
            "u.phone, " +
            "u.regionLocation, " +
            "u.role) " +
            "FROM User AS u " +
            "WHERE u.deleted = false " +
            "ORDER BY u.id ASC")
    List<UserDTO> findAllUserDTOByDeletedIsFalse();

    @Query("SELECT NEW com.cg.model.dto.UserDTO(" +
            "u.id, " +
            "u.imageUrl, " +
            "u.username, " +
            "u.fullName, " +
            "u.phone, " +
            "u.regionLocation, " +
            "u.role) " +
            "FROM User AS u " +
            "JOIN RegionLocation AS region " +
            "ON region.id = u.regionLocation.id " +
            "JOIN Role AS r " +
            "ON r.id = u.role.id " +
            "WHERE " +
            "u.deleted = false " +
            "AND CONCAT(" +
            "u.id," +
            "u.username, " +
            "u.fullName, " +
            "u.phone, " +
            "region.provinceId, " +
            "region.provinceName, " +
            "region.districtId, " +
            "region.districtName, " +
            "region.wardId, " +
            "region.wardName, " +
            "region.address, " +
            "r.roleName) " +
            "LIKE %?1%")
    List<UserDTO> searchUser(String keyword);

    Boolean existsByUsernameAndIdIsNot(String username, Long id);

    Boolean existsByPhoneAndIdIsNot(String phone, Long id);

    Optional<UserDTO> findUserDTOByUsername(String username);
}
