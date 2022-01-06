package com.gangbb.usercenter.controller.user;

import com.gangbb.usercenter.domain.entity.user.User;
import com.gangbb.usercenter.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author Gangbb
 * @date 2021/4/21 9:55
 * @Description:
 */
@RestController
@RequestMapping("/users")
public class UserController {
    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);
    //</editor-fold>
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        log.info("我被请求了");
        return userService.findById(id);
    }
}
