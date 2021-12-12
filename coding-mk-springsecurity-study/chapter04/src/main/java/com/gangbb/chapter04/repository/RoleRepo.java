package com.gangbb.chapter04.repository;

import com.gangbb.chapter04.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Gangbb
 * @ClassName : RoleRepo
 * @Description :
 * @Date : 2021/3/5 8:58
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}
