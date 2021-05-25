package net.holderness.brian.homeindexapi.controller;

import net.holderness.brian.homeindexapi.model.Container;
import net.holderness.brian.homeindexapi.model.Space;
import net.holderness.brian.homeindexapi.service.SpaceService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class SpaceController {

    private final SpaceService spaceService;

    SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping("/space")
    public BigInteger create(@RequestBody Space space) {
        return spaceService.create(space);
    }

    @GetMapping("/space/{id}")
    public Optional<Space> findById(@PathVariable int id) {
        return spaceService.findById(id);
    }

    @GetMapping("/space/{id}/containers")
    public List<Container> getSpaces(@PathVariable int id) {
        return spaceService.getContainers(id);
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @PutMapping("/space/{id}")
    public Space replace(@RequestBody Space space) {
        return spaceService.replace(space);
    }

    @DeleteMapping("/space/{id}")
    public void delete(@PathVariable int id) {
        spaceService.delete(id);
    }
}
