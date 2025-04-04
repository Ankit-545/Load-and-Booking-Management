package com.example.assignment.Controller;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.assignment.Entity.Load;
import com.example.assignment.Services.LoadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/load")
public class LoadController {

    private final LoadService loadService;

    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    @PostMapping
    public ResponseEntity<Load> createLoad(@Valid @RequestBody Load load) {
        Load savedLoad = loadService.createLoad(load);
        return ResponseEntity.ok(savedLoad);
    }

    @GetMapping
    public ResponseEntity<List<Load>> getLoads(
        @RequestParam(required = false) String shipperId,
        @RequestParam(required = false) String truckType,
        @RequestParam(required = false) String productType,
        @RequestParam(required = false) Load.Status status,
        @RequestParam(required = false) String loadingPoint,
        @RequestParam(required = false) String unloadingPoint
) {
    List<Load> loads = loadService.getFilteredLoads(
        shipperId, truckType, productType, status, loadingPoint, unloadingPoint
    );
    return ResponseEntity.ok(loads);
}


    @GetMapping("/{loadId}")
    public ResponseEntity<?> getLoadById(@PathVariable UUID loadId) {
        try {
        Load load = loadService.getLoadById(loadId);
        return ResponseEntity.ok(load);
    } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    }

    @PutMapping("/{loadId}")
public ResponseEntity<?> updateLoad(@PathVariable UUID loadId, @RequestBody @Valid Load updatedLoad) {
   try{
    Load load = loadService.updateLoad(loadId, updatedLoad);
    return ResponseEntity.ok(load);
} catch (Exception ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
}
}

@DeleteMapping("/{loadId}")
public ResponseEntity<?> deleteLoad(@PathVariable UUID loadId) {
    try{
        loadService.deleteLoad(loadId);
    }
    catch (Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
   
    return ResponseEntity.ok().body("Load with id "+loadId + "deleted successfully"); 
}


}
