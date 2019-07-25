package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.ModulesDTO;
import com.johnny.bankworker.dto.ModulesOrderDTO;
import com.johnny.bankworker.service.impl.ModulesServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modules")
public class ModulesController {
    @Autowired
    private ModulesServiceImpl serviceImpl;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public UnifiedResponse findAll(){
        return serviceImpl.findAllModules();
    }

    @RequestMapping(value = "/root", method = RequestMethod.GET)
    public UnifiedResponse findRoot(){
        return serviceImpl.findRootModules();
    }

    @RequestMapping(value = "/child/{parentID}", method = RequestMethod.GET)
    public UnifiedResponse findChildModules(@PathVariable("parentID") int parentID){
        return serviceImpl.findChildModules(parentID);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ModulesDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody ModulesDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/delete/{moduleID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("moduleID") int moduleID){
        return serviceImpl.delete(moduleID);
    }

    @RequestMapping(value="/move", method = RequestMethod.PUT)
    public UnifiedResponse move(@RequestBody ModulesDTO dto){
        return serviceImpl.move(dto);
    }

    @RequestMapping(value="/changeSequence", method = RequestMethod.PUT)
    public UnifiedResponse changeSequence(@RequestBody ModulesOrderDTO dto){
        return serviceImpl.changeSequence(dto);
    }
}
