package com.analysis.service.impl;

import com.analysis.mapper.AlgorithmMapper;
import com.analysis.mapper.DeviceMapper;
import com.analysis.pojo.Algorithm;
import com.analysis.pojo.Device;
import com.analysis.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ManageService")
public class ManageServiceImpl implements ManageService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private AlgorithmMapper algorithmMapper;

    @Override
    public List<Device> queryAllDevice() {
        return deviceMapper.queryAllDevice();
    }

    @Override
    public List<Algorithm> queryAllAlgorithm() {
        return algorithmMapper.queryAllAlgorithm();
    }

}
