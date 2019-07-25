package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BankDTO;
import com.johnny.bankworker.service.impl.BankServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
public class BankController {
    @Autowired
    private BankServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(pageNumber, pageSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("id") int id){
        return serviceImpl.find(id);
    }

    @RequestMapping(value = "/exist/{name}", method = RequestMethod.GET)
    public UnifiedResponse checkExist(@PathVariable("name") String name){
        return serviceImpl.existCheck(name);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody BankDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody BankDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@RequestBody BankDTO dto){
        return serviceImpl.delete(dto);
    }

    @RequestMapping(value="/recovery", method = RequestMethod.PUT)
    public UnifiedResponse recovery(@RequestBody BankDTO dto){
        return serviceImpl.recovery(dto);
    }
}
