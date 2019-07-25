package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.AccountDTO;
import com.johnny.bankworker.service.impl.AccountServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(pageNumber, pageSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("id") int id){
        return serviceImpl.find(id);
    }

    @RequestMapping(value = "/exist/cellphone/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.findCellphone(cellphone);
    }

    @RequestMapping(value = "/login/{cellphone}/{password}", method = RequestMethod.GET)
    public UnifiedResponse login(@PathVariable("cellphone") String cellphone, @PathVariable("password") String password){
        return serviceImpl.findByAccount(cellphone, password);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody AccountDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody AccountDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("id") int id){
        return serviceImpl.delete(id);
    }
}
