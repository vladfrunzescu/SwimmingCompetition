package socialnetwork.domain;

import socialnetwork.utils.Stil;

import java.util.Objects;

public class Concurs extends Entity<Stil> {
    private Integer participanti;

    public Concurs(Integer participanti) {
        this.participanti = participanti;
    }

    public Concurs(Stil stil, Integer participanti) {
        this.setId(stil);
        this.participanti = participanti;
    }

    public Integer getParticipanti() {
        return participanti;
    }

    public void setParticipanti(Integer participanti) {
        this.participanti = participanti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concurs concurs = (Concurs) o;
        return Objects.equals(concurs.getId(), this.getId()) &&
                Objects.equals(concurs.getParticipanti(), this.getParticipanti());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), participanti);
    }
}
