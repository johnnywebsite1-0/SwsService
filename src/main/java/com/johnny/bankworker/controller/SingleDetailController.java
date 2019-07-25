package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.SingleDetailDTO;
import com.johnny.bankworker.service.impl.SingleDetailServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/singleDetail")
public class SingleDetailController {
    @Autowired
    private SingleDetailServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(pageNumber, pageSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("id") int id){
        return serviceImpl.find(id);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody SingleDetailDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody SingleDetailDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/delete/{detailID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("detailID") int detailID){
        return serviceImpl.deleteDetail(detailID);
    }
}
