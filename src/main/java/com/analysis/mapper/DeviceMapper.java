package com.analysis.mapper;

import com.analysis.pojo.Device;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {
    List<Device> queryAllDevice();
}
