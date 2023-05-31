package com.example.moboshardings.structUtils;


import com.example.moboshardings.entity.ProductOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProductOrderStruct {
//    ProductOrderStruct INSTANCE = Mappers.getMapper(ProductOrderStruct.class);

    ProductOrder copyProperties(ProductOrder productOrder);

}
