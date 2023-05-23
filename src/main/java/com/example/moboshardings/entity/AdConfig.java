package com.example.moboshardings.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_config")
public class AdConfig {

    private Long id;
    private String configKey;
    private String configValue;
    private String type;

}
