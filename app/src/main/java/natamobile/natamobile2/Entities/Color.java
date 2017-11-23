package natamobile.natamobile2.Entities;

import java.io.Serializable;
import java.util.List;


public class Color implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private List<Bird> birdList;

    public Color() {
        this.setId(0);
    }

    public Color(Integer id) {
        this.id = id;
    }

    public Color(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bird> getBirdList() {
        return birdList;
    }

    public void setBirdList(List<Bird> birdList) {
        this.birdList = birdList;
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
        if (!(object instanceof Color)) {
            return false;
        }
        Color other = (Color) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.googlevisionexemple.models.Color[ id=" + id + " ]";
    }
    
}
