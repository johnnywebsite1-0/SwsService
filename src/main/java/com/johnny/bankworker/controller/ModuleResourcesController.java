package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.ModuleResourcesDTO;
import com.johnny.bankworker.service.impl.ModuleResourcesServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moduleResources")
public class ModuleResourcesController {
    @Autowired
    private ModuleResourcesServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(pageNumber, pageSize);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ModuleResourcesDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/delete/{resourcesID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("resourcesID") int resourcesID){
        return serviceImpl.deleteResources(resourcesID);
    }

}
