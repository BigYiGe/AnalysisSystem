package com.analysis.service;

import com.analysis.pojo.Algorithm;
import com.analysis.pojo.Device;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ManageService {

    List<Device> queryAllDevice();

    List<Algorithm> queryAllAlgorithm();

}
