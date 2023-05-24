package com.example.moboshardings.strategy;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Description: 复合分片规则
 * date: 2023/5/24 16:25
 * @author mobo
 * @return
 */
public class CustomComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        return null;
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
