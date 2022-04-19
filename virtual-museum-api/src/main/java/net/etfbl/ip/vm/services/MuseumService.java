package net.etfbl.ip.vm.services;

import net.etfbl.ip.vm.models.entities.MuseumEntity;
import net.etfbl.ip.vm.models.entities.TourEntity;
import net.etfbl.ip.vm.repositories.MuseumRepository;
import net.etfbl.ip.vm.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MuseumService {

    private static final String MUSEUMS_DIR = "src/main/resources/static/museums";
    private static final String BASE_URL = "http://localhost:9000/museums/";

    @Autowired
    private MuseumRepository repository;
    @Autowired
    private TourRepository tourRepository;

    public List<MuseumEntity> getAllMuseums(){
        return repository.findAll();
    }
    public MuseumEntity getMuseumById(Integer id) throws Exception{
        MuseumEntity museum = repository.findById(id).orElseThrow(()-> new Exception("Museum not found- id."+id));
        return museum;
    }

    public List<TourEntity> getToursInMuseum(Integer museumId){
        return tourRepository.getToursInMuseum(museumId);


//        try{
//            MuseumEntity museum = getMuseumById(museumId);
//            System.out.println(museum.getName());
//            return museum.getTours();
//        } catch (Exception e){
//            return null;
//        }
    }

    public MuseumEntity addMuseum(MuseumEntity museum){
        return repository.save(museum);
    }

    private String getMuseumDir(String museumName){
       File museumDir = new File(MUSEUMS_DIR + File.separator+museumName);
       if(!museumDir.exists())
           museumDir.mkdir();

       return museumDir.getAbsolutePath();

    }

    public void uploadPhotos(String museumName, List<MultipartFile> files) throws Exception{
        File museumDir = new File(getMuseumDir(museumName));

        for(MultipartFile file : files){
            Files.copy(file.getInputStream(), Paths.get(museumDir.getAbsolutePath()+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }

    }

    public Resource getPresentation(String museumName, String photo) throws Exception{
        String museumDir = getMuseumDir(museumName);
        File[] files = new File(museumDir).listFiles();

        InputStreamResource resource = null;

        for (File f : files){
            if(f.getName().equals(photo))
            {
//                System.out.println(f.getName());

                resource = new InputStreamResource(new FileInputStream(f));
                break;
            }

        }

//        System.out.println(resources);
        return resource;
    }

    public ArrayList<String> getPresentationContent(String museum){
        ArrayList<String> urls = new ArrayList<>();

        String museumDir = getMuseumDir(museum);
        File[] files = new File(museumDir).listFiles();

        for(File file : files){
            urls.add(BASE_URL+museum+"/"+file.getName());
        }

//        System.out.println(urls);
        return urls;


    }

}
