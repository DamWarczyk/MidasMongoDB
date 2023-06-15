package com.example.TestBackend.controller;
import com.example.TestBackend.model.Item;
import  com.example.TestBackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemResource {

    @Autowired
    private final ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<List<Item>> getAllItem(){
        List<Item> items = itemService.findAllItem();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Item> getItemById(@PathVariable("id") String id){
        Item items = itemService.findItemById(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @PostMapping("/add")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Item> postItem( @RequestBody Item item){
        Item item1 = itemService.addItem(item);
        return  new ResponseEntity<>(item1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Item> updateItem( @RequestBody Item item, @PathVariable String id){
        Item updateItem = itemService.updateItem(item, id);
        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin("http://localhost:4200")
    @Transactional
    public ResponseEntity<?> deleteItem ( @PathVariable("id") String id){
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
