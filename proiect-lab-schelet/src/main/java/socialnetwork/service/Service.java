package socialnetwork.service;

import com.sun.javafx.application.ParametersImpl;
import socialnetwork.controller.MessageAlert;
import socialnetwork.domain.*;
import socialnetwork.repository.Repository;
import socialnetwork.utils.Stil;
import socialnetwork.utils.observer.Observable;
import socialnetwork.utils.observer.Observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Service implements Observable {
    private Repository<Stil, Concurs> concursRepo;
    private Repository<Long, Participant> participantRepo;

    public Service(Repository<Stil, Concurs> concursRepo, Repository<Long, Participant> participantRepo) {
        this.participantRepo = participantRepo;
        this.concursRepo = concursRepo;
    }

    @Override
    public void addObserver(Observer e){
        observers.add(e);
    }
    @Override
    public void removeObserver(Observer e){
        observers.remove(e);
    }
    @Override
    public void notifyObservers(){
        observers.stream().forEach(x->x.update());
    }


    public List<Concurs> getAllConcurs(){
        List<Concurs> list = new ArrayList<Concurs>();
        concursRepo.findAll().forEach(list::add);
        return list;
    }

    public List<Concurs> getAllStiluriByParticipant(String nume){
        List<Concurs> concursuri = new ArrayList<>();
        Participant participant = getParticipantByNume(nume);
        for(Stil s : participant.getStiluri()){
            concursuri.add(concursRepo.findOne(s));
        }
        return concursuri;
    }

    public Participant getParticipantByNume(String nume){
        for(Participant p : getAllParticipanti()){
            if(p.getNume().equals(nume) || p.getNume().contains(nume)){
                return p;
            }
        }
        return null;
    }

    public List<Participant> getAllParticipanti(){
        List<Participant> list = new ArrayList<Participant>();
        participantRepo.findAll().forEach(list::add);
        return list;
    }

    public Participant addParticipant(String nume, Integer varsta, List<Stil> list) {
        Participant p = new Participant(nume, varsta, list);
        if(participantRepo.save(p) == null){
            actualizeazaConcursuri(list);
            return null;
        }
        return p;
    }

    public void actualizeazaConcursuri(List<Stil> list){
        for(Stil s : list){
            actualizeazaConcurs(concursRepo.findOne(s));
        }
    }

    public void actualizeazaConcurs(Concurs c){
        c.setParticipanti(c.getParticipanti() + 1);
        concursRepo.update(c);
        notifyObservers();
    }
    /*
    //exp: hotels by location
    //     doi       unu
    public List<Doi> doiByUnu(Long ID){
        List<Doi> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(e.getUnu_id().equals(ID))
                list.add(e);
        }
        return list;
    }

    public List<Unu> getAllUnu(){
        List<Unu> list = new ArrayList<Unu>();
        unuRepo.findAll().forEach(list::add);
        return list;
    }

    public List<Doi> getAllDoi(){
        List<Doi> list = new ArrayList<Doi>();
        doiRepo.findAll().forEach(list::add);
        return list;
    }

    //exp: hotels by location
    //     doi       unu
    public List<Doi> doiByUnuDates(Long ID, LocalDateTime start, LocalDateTime end){
        List<Doi> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(e.getUnu_id().equals(ID) && e.getFirst_date().isAfter(start) && e.getSecond_date().isBefore(end))
                list.add(e);
        }
        return list;
    }

    public List<DoiDTO> personalizate(Unu entity){
        List<DoiDTO> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(true){//e.getFirst_date().isAfter(LocalDateTime.now()) && e.getUnu_id().equals(entity.getId())){
                Unu entitate = unuRepo.findOne(e.getUnu_id());
                DoiDTO dto = new DoiDTO(e.getFirst_myenum(), e.getSecond_myenum(), e.getId(), entitate, e.getFirst_string(), e.getSecond_string(), e.getFirst_Integer(), e.getSecond_Integer(), e.getFirst_integer(), e.getSecond_integer(), e.getFirst_Long(), e.getSecond_Long(), e.getFirst_long(), e.getSecond_long(), e.getFirst_Double(), e.getSecond_Double(), e.getFirst_double(), e.getSecond_double(), e.getFirst_date(), e.getSecond_date());
                list.add(dto);
            }
        }
        return list;
    }


     */
}