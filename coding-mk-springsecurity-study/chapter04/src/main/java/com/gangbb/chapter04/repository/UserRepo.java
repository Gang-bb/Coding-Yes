package com.gangbb.chapter04.repository;

import com.gangbb.chapter04.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Gangbb
 * @ClassName : UserRepo
 * @Description :
 * @Date : 2021/3/5 8:45
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findOptionalByUsername(String username);
}
