package socialnetwork.domain.validators;

import socialnetwork.domain.Participant;

public class ParticipantValidator implements Validator<Participant> {
    @Override
    public void validate(Participant entity) throws ValidationException {
        String errors = "";

        if(entity.getId() == null || entity.getId() < 0){
            errors += "Id invalid!\n";
        }

        if(entity.getNume() == null || entity.getNume().equals("")){
            errors += "Nume invalid!\n";
        }

        if(entity.getVarsta() == null || entity.getVarsta() < 0) {
            errors += "Varsta invalida!\n";
        }

        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}
