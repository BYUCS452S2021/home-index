package net.holderness.brian.homeindexapi.controller;

import net.holderness.brian.homeindexapi.model.Item;
import net.holderness.brian.homeindexapi.model.Item;
import net.holderness.brian.homeindexapi.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private final ItemService itemService;

    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public BigInteger create(@RequestBody Item item) {
        return itemService.create(item);
    }

    @GetMapping("/item/{id}")
    public Optional<Item> findById(@PathVariable int id) {
        return itemService.findById(id);
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @PutMapping("/item/{id}")
    public Item replace(@RequestBody Item item) {
        return itemService.replace(item);
    }

    @DeleteMapping("/item/{id}")
    public void delete(@PathVariable int id) {
        itemService.delete(id);
    }
}
