package com.example.moboshardings.strategy;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * Description: 复合分片规则
 * date: 2023/5/24 16:25
 * @author mobo
 * @return
 */
public class CustomComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {
    /**
     * @param dataSourceNames 数据源集合
     *                        在分库时值为所有分片库的集合 databaseNames：比如表：product_order_0/product_order_1、库ds0/ds1 等
     *                        分表时为对应分片库中所有分片表的集合 tablesNames
     * @param shardingValue   分片属性，包括
     *                        logicTableName 为逻辑表，
     *                        columnNameAndShardingValuesMap 存储多个分片健，包括key-value
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> dataSourceNames, ComplexKeysShardingValue<Long> shardingValue) {
        //得到每个分片键对应的值
        Collection<Long> orderIdValues = this.getShardingValue(shardingValue, "id");
        Collection<Long> userIdValue = this.getShardingValue(shardingValue, "user_id");
        List<String> shardingSuffix = new ArrayList<>();
        for (Long userId : userIdValue) {
            for (Long id: orderIdValues) {
                //同时使用两个分片键，如userid=2，id=3 时，对应表的后缀为_0_1
                String suffix = userId % 2 + "_" + id % 2;
                for (String dataSourceName : dataSourceNames) {
                    if (dataSourceName.endsWith(suffix)) {
                        shardingSuffix.add(dataSourceName);
                    }
                }
            }
        }
        return shardingSuffix;
    }


    /**
     * shardingValue  分片属性，包括
     * logicTableName 为逻辑表，
     * columnNameAndShardingValuesMap 存储多个分片健 包括key-value
     * key：分片key，id和user_id
     * value：分片value，66和99
     *
     * @return shardingValues 集合
     */
    private Collection<Long> getShardingValue(ComplexKeysShardingValue<Long> shardingValues, final String key) {
        Collection<Long> valueSet = new ArrayList<>();
        Map<String, Collection<Long>> columnNameAndShardingValuesMap = shardingValues.getColumnNameAndShardingValuesMap();
        if (columnNameAndShardingValuesMap.containsKey(key)) {
            valueSet.addAll(columnNameAndShardingValuesMap.get(key));
        }
        return valueSet;
    }
}
