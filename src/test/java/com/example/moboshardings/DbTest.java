package com.example.moboshardings;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
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
import java.util.List;
import java.util.Random;
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
    public void testSaveProductOrder() {

        for (int i = 0; i < 20; i++) {
            ProductOrder productOrder = new ProductOrder();
            productOrder.setCreateTime(new Date());
            productOrder.setNickname("二当家分库分表i=" + i);
            productOrder.setOutTradeNo(UUID.randomUUID().toString().substring(0, 32));
            productOrder.setPayAmount(100.00);
            productOrder.setState("PAY");
            productOrder.setUserId(Long.valueOf(i + ""));
            productOrderMapper.insert(productOrder);
        }
    }

    @Test
    public void testSaveProductOrderWithClass() {
        Random random = new Random();
        for(int i=0;i<10;i++){
            ProductOrder productOrder = new ProductOrder();
            productOrder.setCreateTime(new Date());
            productOrder.setNickname("分库分表二当家i="+i);
            productOrder.setOutTradeNo(UUID.randomUUID().toString().substring(0,32));
            productOrder.setPayAmount(100.00);
            productOrder.setState("PAY");
            int value = random.nextInt(100);
            productOrder.setUserId(Long.valueOf(value));
            productOrderMapper.insert(productOrder);
        }
    }

    @Test
    public void testSaveAdConfig() {

        AdConfig adConfigDO = new AdConfig();
        adConfigDO.setConfigKey("banner");
        adConfigDO.setConfigValue("xdclass.net");
        adConfigDO.setType("ad");

        adConfigMapper.insert(adConfigDO);

    }

    @Test
    public void testBinding() {
        List<Object> objects = productOrderMapper.listProduct();
        System.out.println(objects);
    }


    /**
     * Description: 有分片键
     * date: 2023/5/24 14:17
     * @return void
     */
    @Test
    public void testPartitionKeySelect() {
        productOrderMapper.selectList(new LambdaQueryWrapper<ProductOrder>().eq(ProductOrder::getId, 2L));
    }

    /**
     * Description: 无分片键，直接跑全库
     * date: 2023/5/24 14:54
     * @return void
     */
    @Test
    public void testNoPartitionKeySelect() {
//        productOrderMapper.selectList(new LambdaQueryWrapper<ProductOrder>().eq(ProductOrder::getOutTradeNo, 1));
        //包含分片键即可
        productOrderMapper.selectList(new LambdaQueryWrapper<ProductOrder>().eq(ProductOrder::getOutTradeNo, 1).eq(ProductOrder::getId, 2L));
    }

    /**
     * Description: 范围分片测试
     * date: 2023/5/24 16:17
     * @return void
     */
    @Test
    public void testRangSelect() {
//        productOrderMapper.selectList(new LambdaQueryWrapper<ProductOrder>().between(ProductOrder::getId,1L,1L));
        productOrderMapper.selectList(new LambdaQueryWrapper<ProductOrder>().between(ProductOrder::getId,1L,3L));
    }
}