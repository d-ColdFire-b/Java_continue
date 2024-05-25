package entity;

import java.util.Objects;

public class Currency {

    int id;
    String fullName, isoName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }


    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", isoName='" + isoName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return id == currency.id && Objects.equals(fullName, currency.fullName) && Objects.equals(isoName, currency.isoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, isoName);
    }
}
