package com.peasch.controller;

import com.peasch.model.dto.copies.CopyDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;
import com.peasch.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/copies")
public class CopyController {

    @Autowired
    private CopyService service;


    @GetMapping
    public List<CopyDto> getCopies(){
        return service.getCopies();
    }

    @GetMapping("{id}")
    public CopyDto getCopyById(@PathVariable(value = "id")Integer id){
        return service.findById(id);
    }

    @PostMapping("add")
    public void addCopy (@RequestBody CopyWithALLDTO copy){
        service.save(copy);
    }

    @GetMapping("book/{id}")
    public Map<Integer,Integer> getCopiesofBookInLibraries(@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token){
        return service.findCopiesInLibrary(id);
    }

    @GetMapping("quantities/available/{id}")
    public int getNumberOfCopiesAvailable(@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token){
        return service.findNumberOfCopiesAvailable(id);
    }
    @GetMapping("quantities/{id}")
    public int getNumberOfCopies(@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token){
        return service.findNumberOfCopies(id);
    }
}
