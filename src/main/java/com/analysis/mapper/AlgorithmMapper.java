package com.analysis.mapper;

import com.analysis.pojo.Algorithm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlgorithmMapper {
    List<Algorithm> queryAllAlgorithm();
}
