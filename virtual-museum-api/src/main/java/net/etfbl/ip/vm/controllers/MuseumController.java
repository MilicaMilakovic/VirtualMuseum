package net.etfbl.ip.vm.controllers;

import net.etfbl.ip.vm.models.entities.MuseumEntity;
import net.etfbl.ip.vm.models.entities.TourEntity;
import net.etfbl.ip.vm.services.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/v1/museums")
public class MuseumController {

    @Autowired
    private MuseumService museumService;

    @GetMapping
    public List<MuseumEntity> findAll(){
        return museumService.getAllMuseums();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MuseumEntity> getMuseumById(@PathVariable Integer id){
       try{
           MuseumEntity museum = museumService.getMuseumById(id);
           return ResponseEntity.ok(museum);
       } catch (Exception e){
           return ResponseEntity.notFound().build();
       }
    }

    @GetMapping ("/{id}/tours")
    public ResponseEntity<List<TourEntity>> getToursInMuseum(@PathVariable Integer id){

        return ResponseEntity.ok(museumService.getToursInMuseum(id));

//        System.out.println(museumId);
//        List<TourEntity> tours = museumService.getToursInMuseum(museumId);
//        if (tours!=null)
//            return ResponseEntity.ok(tours);
//
//        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MuseumEntity> addMuseum(@RequestBody MuseumEntity museum){
        MuseumEntity museumEntity = museumService.addMuseum(museum);
        if(museumEntity!= null)
            return ResponseEntity.ok(museumEntity);
        return ResponseEntity.badRequest().build();

    }

    @PostMapping(value = "/upload/{name}", consumes = { MediaType.APPLICATION_JSON_VALUE ,MediaType.MULTIPART_FORM_DATA_VALUE})
    public void uploadImage(@PathVariable String name, @RequestPart("file")List<MultipartFile>file){
//        System.out.println(name);
//        file.forEach(e -> System.out.println(e.getOriginalFilename()));

        try{
            museumService.uploadPhotos(name, file);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/presentation/{name}/{photo}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getPresentation(@PathVariable String name,@PathVariable String photo){
        Resource resourcee = null;
        try{
            resourcee = museumService.getPresentation(name,photo);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(resourcee);
    }

    @GetMapping(value = "/presentation/{name}")
    public ResponseEntity<ArrayList<String>> getPresentationContent(@PathVariable String name){
        ArrayList<String> urls = museumService.getPresentationContent(name);
        return ResponseEntity.ok(urls);
    }

}
