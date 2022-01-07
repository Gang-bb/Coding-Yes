package com.gangbb.contentcenter.controller;

import com.gangbb.contentcenter.domain.vo.ShareVo;
import com.gangbb.contentcenter.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gangbb
 * @date 2021/4/21 16:39
 * @Description:
 */
@RestController
@RequestMapping("/shares")
public class ShareController {
    @Autowired
    private ShareService shareService;

    @GetMapping("/{id}")
    public ShareVo findById(@PathVariable Integer id) {
        return this.shareService.findById(id);
    }
}
