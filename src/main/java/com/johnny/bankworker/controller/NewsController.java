package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.NewsDTO;
import com.johnny.bankworker.service.impl.NewsServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private NewsServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(pageNumber, pageSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("id") int id){
        return serviceImpl.find(id);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody NewsDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody NewsDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/delete/{newsID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("newsID") int newsID){
        return serviceImpl.deleteNews(newsID);
    }
}
