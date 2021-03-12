package socialnetwork.repository.file;

import socialnetwork.domain.Concurs;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.Constants;
import socialnetwork.utils.My_Enum;
import socialnetwork.utils.Stil;

import java.time.LocalDateTime;
import java.util.List;

public class ConcursFile extends AbstractFileRepository<Stil, Concurs> {
    public ConcursFile(String fileName, Validator<Concurs> validator) {
        super(fileName, validator);
    }

    @Override
    public Concurs extractEntity(List<String> attributes) {
        Stil first_myenum = Stil.valueOf(attributes.get(0));
        Integer first_Integer = Integer.parseInt(attributes.get(1));
        Concurs entity = new Concurs(first_myenum, first_Integer);
        return entity;
    }

    @Override
    protected String createEntityAsString(Concurs entity) {
        return entity.getId().toString()+";"+entity.getParticipanti();
    }
}
