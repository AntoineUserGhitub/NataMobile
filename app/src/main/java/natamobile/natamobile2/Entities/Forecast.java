package natamobile.natamobile2.Entities;

import java.io.Serializable;
import java.util.List;



public class Forecast implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String forecastDescription;

    public Forecast() {
        this.setId(0);
    }

    public Forecast(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForecastDescription() {
        return forecastDescription;
    }

    public void setForecastDescription(String forecastDescription) {
        this.forecastDescription = forecastDescription;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Forecast)) {
            return false;
        }
        Forecast other = (Forecast) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.googlevisionexemple.models.Forecast[ id=" + id + " ]";
    }
    
}
