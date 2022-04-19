package net.etfbl.ip.vm.controllers;

import net.etfbl.ip.vm.models.entities.TourEntity;
import net.etfbl.ip.vm.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/v1/tours")
public class TourController {
    @Autowired
    private TourService tourService;

    @GetMapping
    public ResponseEntity<List<TourEntity>> getTours(){
        return ResponseEntity.ok(tourService.getAllTours());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourEntity> getTourById(@PathVariable(name = "id") Integer id){
        try{
            TourEntity tour = tourService.getTourById(id);
            return ResponseEntity.ok(tour);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TourEntity> addTour(@RequestBody TourEntity tourEntity){
        TourEntity tour = tourService.addTour(tourEntity);
        if(tour!=null)
            return ResponseEntity.ok(tour);
        return ResponseEntity.badRequest().build();
    }

}
