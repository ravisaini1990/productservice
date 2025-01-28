package com.ravi.productservice.repository;

import com.ravi.productservice.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, Integer> {

    User getUserByEmail(String email);
}
