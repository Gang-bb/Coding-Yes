package com.gangbb.contentcenter.domain.entity.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "share")
@Builder
public class Share {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 标题
     */
    private String title;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否是原创 0-否 1-是
     */
    @Column(name = "is_original")
    private Byte isOriginal;

    /**
     * 作者
     */
    private String author;

    /**
     * 封面
     */
    private String cover;

    /**
     * 概要信息
     */
    private String summary;

    /**
     * 价格(需要的积分)
     */
    private Integer price;

    /**
     * 下载地址
     */
    @Column(name = "download_url")
    private String downloadUrl;

    /**
     * 下载数
     */
    @Column(name = "buy_count")
    private Integer buyCount;

    /**
     * 是否显示 0-否 1-是
     */
    @Column(name = "show_flag")
    private Byte showFlag;

    /**
     * 审核状态  NOT_YET: 待审核  PASSED: 审核通过
     */
    @Column(name = "audit_status")
    private String auditStatus;

    /**
     * 审核不通过原因
     */
    private String reason;
}