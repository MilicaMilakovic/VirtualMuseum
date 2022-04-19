package net.etfbl.ip.vm.services;

import net.etfbl.ip.vm.models.entities.TicketEntity;
import net.etfbl.ip.vm.models.entities.TourEntity;
import net.etfbl.ip.vm.models.entities.UserEntity;
import net.etfbl.ip.vm.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class TourService {

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private EmailServiceImpl emailService;

    public List<TourEntity> getAllTours(){
        return tourRepository.findAll();
    }

    public TourEntity getTourById(Integer id) throws Exception{
        TourEntity tour = tourRepository.findById(id).orElseThrow(()-> new Exception("Tour not found. id-" + id));
        return  tour;
    }

    public TourEntity addTour(TourEntity tour){
        return tourRepository.save(tour);
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void notifyUsersAboutTour(){

//        System.out.println("send mail....");
        List<TourEntity> tours = getAllTours();
        List<UserEntity> users = new ArrayList<>();

        Date now = new Date();
        String nowString = now.toString();

//        System.out.println(nowString);
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<TourEntity> toursAboutToStart = new ArrayList<>();
        List<TourEntity> toursAboutToEnd = new ArrayList<>();

//        System.out.println("toursAboutToStart");

        toursAboutToStart = tours.stream().filter( e -> {
            Timestamp tourStart = e.getStart();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tourStart);
            calendar.add(Calendar.HOUR_OF_DAY, -1);

            return nowString.equals(calendar.getTime().toString());

        }).collect(Collectors.toList());

//        System.out.println("toursAboutToEnd");
        toursAboutToEnd = tours.stream().filter( e -> {
            Timestamp tourStart = e.getStart();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tourStart);
            calendar.add(Calendar.HOUR_OF_DAY, e.getDuration().intValue());

            calendar.add(Calendar.MINUTE, -5);

            return  nowString.equals(calendar.getTime().toString());

        }).collect(Collectors.toList());


        toursAboutToStart.stream().forEach(e -> {
//            System.out.println("START : " + e.getStart() + " " +e.getId());
            for(TicketEntity t : e.getTickets()){
                UserEntity user = t.getUser();
                emailService.sendEmail(user.getEmail(), "Just a reminder - the tour 'Explore " + e.getMuseum().getName() +"' will start in an hour!");
            }
        });

        toursAboutToEnd.stream().forEach(e -> {
//            System.out.println("END : " + e.getStart() + " " +e.getId());
            for(TicketEntity t : e.getTickets()){
                UserEntity user = t.getUser();
                emailService.sendEmail(user.getEmail(), "The tour 'Explore " + e.getMuseum().getName() +"' ends in 5 minutes!");
            }
        });

//        System.out.println("-----------------");
    }
}
