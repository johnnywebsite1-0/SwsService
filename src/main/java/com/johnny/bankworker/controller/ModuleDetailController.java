package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.ModuleDetailDTO;
import com.johnny.bankworker.service.impl.ModuleDetailServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moduleDetail")
public class ModuleDetailController {
    @Autowired
    private ModuleDetailServiceImpl serviceImpl;

    @RequestMapping(value = "/module/{moduleID}", method = RequestMethod.GET)
    public UnifiedResponse findChildModules(@PathVariable("moduleID") int moduleID){
        return serviceImpl.findListOfModule(moduleID);
    }

    @RequestMapping(value = "/module/parent/{parentModuleID}", method = RequestMethod.GET)
    public UnifiedResponse findListOfParentModule(@PathVariable("parentModuleID") int parentModuleID){
        return serviceImpl.findListOfParentModule(parentModuleID);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ModuleDetailDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody ModuleDetailDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/delete/{moduleID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("moduleID") int moduleID){
        return serviceImpl.deleteDetail(moduleID);
    }

}
