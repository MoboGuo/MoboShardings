package com.example.moboshardings.strategy;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Description:
 * date: 2023/5/24 15:44
 * @author mobo
 * @return
 */
public class CustomRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    /**
     *
     * @param dataSourceNames 数据源集合
     *                      在分库时值为所有分片库的集合 databaseNames
     *                      分表时为对应分片库中所有分片表的集合 tablesNames
     *
     * @param rangeShardingValue  分片属性，包括
     *                                  logicTableName 为逻辑表，
     *                                  columnName 分片健（字段），
     *                                  valueRange 为从 SQL 中解析出的分片健的值
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> dataSourceNames, RangeShardingValue<Long> rangeShardingValue) {
        Set<String> result = new LinkedHashSet<>();
        //起始值
        Long lower = rangeShardingValue.getValueRange().lowerEndpoint();
        //结束值
        Long higher = rangeShardingValue.getValueRange().upperEndpoint();

        //循环计算分库逻辑
        for (long i = lower; i <= higher; i++) {
            for (String dataSourceName : dataSourceNames) {
                if (dataSourceName.endsWith(i % dataSourceNames.size() + "")) {
                    result.add(dataSourceName);
                }
            }
        }
        return result;
    }
}
