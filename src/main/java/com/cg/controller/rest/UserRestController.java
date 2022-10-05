package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Role;
import com.cg.model.User;
import com.cg.model.dto.RoleDTO;
import com.cg.model.dto.UserDTO;
import com.cg.model.dto.UserUpdateDTO;
import com.cg.service.role.IRoleService;
import com.cg.service.user.IUserService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private AppUtil appUtil;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoleDTO() {
        List<RoleDTO> roleDTO = roleService.findAllRoleDTO();
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUserDTO() {
        List<UserDTO> userDTO = userService.findAllUserDTOByDeletedIsFalse();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("Invalid user ID!");
        }
        return new ResponseEntity<>(userOptional.get().toUserDTO(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        userDTO.setId(0L);
        userDTO.setImageUrl("user_icon.png");
        userDTO.getRegionLocation().setId(0L);

        Boolean existsById = userService.existsById(userDTO.getId());
        if (existsById) {
            throw new EmailExistsException("User ID existed!");
        }

        Boolean existsByUsername = userService.existsByUsername(userDTO.getUsername());
        if (existsByUsername) {
            throw new EmailExistsException("Username existed!");
        }

        Boolean existsByPhone = userService.existsByPhone(userDTO.getPhone());
        if (existsByPhone) {
            throw new EmailExistsException("Phone numbers existed!");
        }

        Optional<Role> role = roleService.findById(userDTO.getRole().getId());
        if (!role.isPresent()){
            throw new EmailExistsException("Role ID doesn't existed!");
        }

        try{
            User user = userService.save(userDTO.toUser());
            return new ResponseEntity<>(user.toUserDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            throw new DataInputException("Data error!");
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody UserUpdateDTO userUpdateDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return appUtil.mapErrorToResponse(bindingResult);
        }

        Boolean existsById = userService.existsById(userUpdateDTO.getId());
        if (!existsById) {
            throw new EmailExistsException("ID doesn't exist!");
        }

        Boolean existsByPhone = userService.existsByPhoneAndIdIsNot(userUpdateDTO.getPhone(), userUpdateDTO.getId());
        if (existsByPhone) {
            throw new EmailExistsException("Phone numbers existed!");
        }

        Optional<Role> roleId = roleService.findById(userUpdateDTO.getRole().getId());
        if (!roleId.isPresent()) {
            throw new EmailExistsException("Role ID doesn't exist!");
        }

        userUpdateDTO.getRegionLocation().setId(0L);

        try {
            User updateUser = userService.saveUpdate(userUpdateDTO.toUser());
            return new ResponseEntity<> (updateUser.toUserDTO(), HttpStatus.ACCEPTED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Data error!");
        }
    }

    @PatchMapping("/remove/{id}")
    public ResponseEntity<?> getAllUserDTO(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent()) {
            user.get().setDeleted(true);
            userService.save(user.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            throw new DataInputException("Data error!");
        }
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchUserDTO(@PathVariable String keyword) {
        List<UserDTO> userDTO = userService.searchUser(keyword);
        if (userDTO.isEmpty()) {
            throw new DataInputException("There's no result for this search!");
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
