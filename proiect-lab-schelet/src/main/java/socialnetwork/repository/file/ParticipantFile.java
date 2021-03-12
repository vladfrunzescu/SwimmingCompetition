package socialnetwork.repository.file;

import socialnetwork.domain.Participant;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.Constants;
import socialnetwork.utils.My_Enum;
import socialnetwork.utils.Stil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticipantFile extends AbstractFileRepository<Long, Participant> {
    public ParticipantFile(String fileName, Validator<Participant> validator) {
        super(fileName, validator);
    }

    @Override
    public Participant extractEntity(List<String> attributes) {
        Long ID = Long.parseLong(attributes.get(0));
        String first_string = attributes.get(1);
        Integer first_Integer = Integer.parseInt(attributes.get(2));

        List<String> attr = Arrays.asList(attributes.get(3).split(","));
        List<Stil> list = new ArrayList<>();
        for(String a : attr){
            list.add(Stil.valueOf(a));
        }

        Participant entity = new Participant(ID, first_string, first_Integer, list);
        return entity;
    }

    @Override
    protected String createEntityAsString(Participant entity) {
        String stil = "";
        for(Stil s : entity.getStiluri()){
            stil = stil + s + ",";
        }
        String finalStil = stil.substring(0, stil.length()-1);
        return entity.getId()+";"+entity.getNume()+";"+entity.getVarsta()+";"+finalStil;
    }
}
