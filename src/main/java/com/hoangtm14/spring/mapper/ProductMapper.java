package com.hoangtm14.spring.mapper;

import com.hoangtm14.spring.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
}
