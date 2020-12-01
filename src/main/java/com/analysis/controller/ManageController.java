package com.analysis.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.analysis.pojo.Algorithm;
import com.analysis.pojo.Device;
import com.analysis.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    @Qualifier("ManageService")
    private ManageService manageService;

    @RequestMapping("/device")
    public String DeviceManage(Model model) {
        model.addAttribute("devices", manageService.queryAllDevice());
        return "device-manage";
    }

    @RequestMapping("/algorithm")
    public String AlgorithmManage(Model model) {
        List<Algorithm> algorithms = manageService.queryAllAlgorithm();
        String Jsonstr = JSON.toJSONString(algorithms);
        model.addAttribute("algorithms", Jsonstr);
        return "algorithm-manage";
    }
}
