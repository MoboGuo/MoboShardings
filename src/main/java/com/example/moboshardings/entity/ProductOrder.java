package com.example.moboshardings.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mobo
 */
@Data
@TableName("product_order")
@EqualsAndHashCode(callSuper = false)
public class ProductOrder implements Serializable {

    //使用配置文件管理时不需要在此处配置
    //    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String outTradeNo;

    private String state;

    private Date createTime;

    private Double payAmount;

    private String nickname;

    private Long userId;
}
