package com.example.moboshardings;

import com.example.moboshardings.entity.AdConfig;
import com.example.moboshardings.entity.ProductOrder;
import com.example.moboshardings.mapper.AdConfigMapper;
import com.example.moboshardings.mapper.ProductOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes = MoboShardingsApplication.class)
@Slf4j
public class DbTest {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private AdConfigMapper adConfigMapper;

    @Test
    public void testSaveProductOrder(){

        for(int i=0;i<20;i++){
            ProductOrder productOrder = new ProductOrder();
            productOrder.setCreateTime(new Date());
            productOrder.setNickname("二当家分库分表i="+i);
            productOrder.setOutTradeNo(UUID.randomUUID().toString().substring(0,32));
            productOrder.setPayAmount(100.00);
            productOrder.setState("PAY");
            productOrder.setUserId(Long.valueOf(i+""));
            productOrderMapper.insert(productOrder);
        }
    }

    @Test
    public void testSaveAdConfig(){

        AdConfig adConfigDO = new AdConfig();
        adConfigDO.setConfigKey("banner");
        adConfigDO.setConfigValue("xdclass.net");
        adConfigDO.setType("ad");

        adConfigMapper.insert(adConfigDO);

    }
}