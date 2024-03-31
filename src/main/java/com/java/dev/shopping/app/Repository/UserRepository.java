package com.java.dev.shopping.app.Repository;

import com.java.dev.shopping.app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
