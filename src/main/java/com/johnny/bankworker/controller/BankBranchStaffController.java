package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BankBranchStaffDTO;
import com.johnny.bankworker.service.impl.BankBranchStaffServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/branch/staff")
public class BankBranchStaffController {
    @Autowired
    private BankBranchStaffServiceImpl serviceImpl;

    @RequestMapping(value = "/{bankID}/{branchID}/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("bankID") int bankID, @PathVariable("branchID") int branchID, @PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(bankID, branchID, pageNumber, pageSize);
    }

    @RequestMapping(value = "/{bankID}/{branchID}/{staffID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("bankID") int bankID, @PathVariable("branchID") int branchID, @PathVariable("staffID") int staffID){
        return serviceImpl.find(bankID, branchID, staffID);
    }

    @RequestMapping(value = "/{bankID}/{branchID}/exist/{staffName}", method = RequestMethod.GET)
    public UnifiedResponse checkExist(@PathVariable("bankID") int bankID, @PathVariable("branchID") int branchID, @PathVariable("staffName") String staffName){
        return serviceImpl.existCheck(bankID, branchID, staffName);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody BankBranchStaffDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody BankBranchStaffDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@RequestBody BankBranchStaffDTO dto){
        return serviceImpl.delete(dto);
    }

    @RequestMapping(value="/recovery", method = RequestMethod.PUT)
    public UnifiedResponse recovery(@RequestBody BankBranchStaffDTO dto){
        return serviceImpl.recovery(dto);
    }
}
