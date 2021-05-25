package net.holderness.brian.homeindexapi.controller;

import net.holderness.brian.homeindexapi.model.Container;
import net.holderness.brian.homeindexapi.model.Container;
import net.holderness.brian.homeindexapi.model.Item;
import net.holderness.brian.homeindexapi.service.ContainerService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class ContainerController {

    private final ContainerService containerService;

    ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping("/container")
    public BigInteger create(@RequestBody Container container) {
        return containerService.create(container);
    }

    @GetMapping("/container/{id}")
    public Optional<Container> findById(@PathVariable int id) {
        return containerService.findById(id);
    }

    @GetMapping("/container/{id}/items")
    public List<Item> getContainers(@PathVariable int id) {
        return containerService.getItems(id);
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @PutMapping("/container/{id}")
    public Container replace(@RequestBody Container container) {
        return containerService.replace(container);
    }

    @DeleteMapping("/container/{id}")
    public void delete(@PathVariable int id) {
        containerService.delete(id);
    }
}
