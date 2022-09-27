package com.example.demo.controller;

import com.example.demo.entity.Barang;
import com.example.demo.service.impl.BarangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barang")
public class BarangController {

    @Autowired
    private BarangServiceImpl service;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllData(){
        return new ResponseEntity<>(service.findAllData() , HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveData(@RequestBody Barang param){
        return new ResponseEntity<>(service.save(param) , HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateData(@PathVariable Long id,
                                        @RequestBody Barang param){
       Barang data = service.update(param, id);

       if (data != null){
           return new ResponseEntity<>(HttpStatus.OK);
       }else {
           return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
       }
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?>findById2(@RequestParam(name = "id") Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?>deleteData(@PathVariable Long id){
        if (service.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

}