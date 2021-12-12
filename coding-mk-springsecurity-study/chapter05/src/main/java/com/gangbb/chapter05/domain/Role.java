package com.gangbb.chapter05.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author : Gangbb
 * @ClassName : Role
 * @Description :
 * @Date : 2021/3/5 7:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "mooc_roles")
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增长 ID，唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称，有唯一约束，不能重复
     */
    @NotNull
    @Size(max = 50)
    @Column(name = "role_name", unique = true, nullable = false, length = 50)
    private String authority;

    public Role(@NotNull @Size(max = 50) String authority) {
        this.authority = authority;
    }
}