package com.example.Tasks.Repositories;

import com.example.Tasks.DTOs.UserDTOs.GetUserDTO;
import com.example.Tasks.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT new com.example.Tasks.DTOs.UserDTOs.GetUserDTO(u.id,u.firstName,u.lastName,u.email,u.username,u.phone) FROM User u WHERE u.username=:username")
    Optional<GetUserDTO> findByUsername(@Param("username") String username);
    Optional<User> findByEmail(String email);

    @Query("SELECT new com.example.Tasks.DTOs.UserDTOs.GetUserDTO(u.id,u.firstName,u.lastName,u.email,u.username,u.phone) FROM User u")
    List<GetUserDTO> findAllUsers();
}
