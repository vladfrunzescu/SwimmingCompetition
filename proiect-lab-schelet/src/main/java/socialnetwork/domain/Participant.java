package socialnetwork.domain;

import socialnetwork.utils.Stil;

import java.util.List;
import java.util.Objects;

public class Participant extends  Entity<Long>{

    private String nume;
    private Integer varsta;
    private List<Stil> stiluri;

    public Participant(String nume, Integer varsta, List<Stil> stiluri) {
        this.setId(generateRandomId());
        this.nume = nume;
        this.varsta = varsta;
        this.stiluri = stiluri;
    }

    public Participant(Long id, String nume, Integer varsta, List<Stil> stiluri) {
        this.setId(id);
        this.nume = nume;
        this.varsta = varsta;
        this.stiluri = stiluri;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public List<Stil> getStiluri() {
        return stiluri;
    }

    public void setStiluri(List<Stil> stiluri) {
        this.stiluri = stiluri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(nume, that.nume) &&
                Objects.equals(varsta, that.varsta) &&
                Objects.equals(stiluri, that.stiluri) &&
                Objects.equals(that.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), nume, varsta, stiluri);
    }

    private Long generateRandomId() {
        long leftLimit = 1L;
        long rightLimit = 1000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
