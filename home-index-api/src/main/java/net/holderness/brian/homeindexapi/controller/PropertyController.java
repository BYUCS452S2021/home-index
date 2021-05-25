package net.holderness.brian.homeindexapi.controller;

import net.holderness.brian.homeindexapi.model.Property;
import net.holderness.brian.homeindexapi.model.Space;
import net.holderness.brian.homeindexapi.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class PropertyController {

    private final PropertyService propertyService;

    PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/property")
    public BigInteger create(@RequestBody Property property) {
        return propertyService.create(property);
    }

    @GetMapping("/property/{id}")
    public Optional<Property> findById(@PathVariable int id) {
        return propertyService.findById(id);
    }

    @GetMapping("/property/{id}/spaces")
    public List<Space> getSpaces(@PathVariable int id) {
        return propertyService.getSpaces(id);
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @PutMapping("/property/{id}")
    public Property replace(@RequestBody Property property) {
        return propertyService.replace(property);
    }

    @DeleteMapping("/property/{id}")
    public void delete(@PathVariable int id) {
        propertyService.delete(id);
    }
}
