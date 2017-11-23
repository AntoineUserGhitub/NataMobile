package natamobile.natamobile2.Entities;

import java.io.Serializable;
import java.util.List;



public class Bird implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private int height;
    private List<Color> colorList;
    

    public Bird() {
        this.setId(0);
    }

    public Bird(Integer id) {
        this.id = id;
    }

    public Bird(Integer id, String name, int height) {
        this.id = id;
        this.name = name;
        this.height = height;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
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
        if (!(object instanceof Bird)) {
            return false;
        }
        Bird other = (Bird) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.googlevisionexemple.models.Bird[ id=" + id + " ]";
    }
    
}
