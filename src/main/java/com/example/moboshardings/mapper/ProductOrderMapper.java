package com.example.moboshardings.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.moboshardings.entity.ProductOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductOrderMapper extends BaseMapper<ProductOrder> {

    @Select("select * from product_order o left join product_order_item oi on o.id= oi.product_order_id")
    List<Object> listProduct();
}
