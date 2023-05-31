package com.example.moboshardings;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.moboshardings.entity.AdConfig;
import com.example.moboshardings.entity.ProductOrder;
import com.example.moboshardings.mapper.AdConfigMapper;
import com.example.moboshardings.mapper.ProductOrderMapper;
import com.example.moboshardings.structUtils.ProductOrderStruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes = MoboShardingsApplication.class)
@Slf4j
public class DbTest {

    @Resource
    private ProductOrderMapper productOrderMapper;
    @Resource
    private  AdConfigMapper adConfigMapper;

    @Resource
    private ProductOrderStruct productOrderStruct;


    public DbTest() {
    }

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

    /**
     * Description: 测试复合分片
     * date: 2023/5/29 10:10
     * @return void
     */
    @Test
    public void testComplexSelect() {
        productOrderMapper.selectList(new LambdaQueryWrapper<ProductOrder>().eq(ProductOrder::getId,66L).eq(ProductOrder::getUserId,99L));
    }


    @Test
    public void testHint() {
        // 清除掉历史的规则
        HintManager.clear();
        //Hint分片策略必须要使用 HintManager工具类
        HintManager hintManager = HintManager.getInstance();
        // 设置库的分片健,value用于库分片取模，
        hintManager.addDatabaseShardingValue("product_order",3L);

        // 设置表的分片健,value用于表分片取模，
        //hintManager.addTableShardingValue("product_order", 7L);
        hintManager.addTableShardingValue("product_order", 8L);

        // 如果在读写分离数据库中，Hint 可以强制读主库（主从复制存在一定延时，但在业务场景中，可能更需要保证数据的实时性）
        //hintManager.setMasterRouteOnly();

        //对应的value只做查询，不做sql解析
        productOrderMapper.selectList(new QueryWrapper<ProductOrder>().eq("id", 66L));
    }

    @Test
    public void  testMapStruct() {
        ProductOrder productOrder = productOrderMapper.selectOne(new LambdaQueryWrapper<ProductOrder>().eq(ProductOrder::getId, 1661034226226511874L));
        ProductOrder newRecord = productOrderStruct.copyProperties(productOrder);
        System.out.println(newRecord);
    }
}