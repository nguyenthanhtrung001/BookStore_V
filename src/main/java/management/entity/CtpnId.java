package management.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CtpnId implements java.io.Serializable {

    @Column(name = "MAPN", nullable = false)
    private int mapn;

    @Column(name = "MAMH", nullable = false)
    private int mamh;

    @Column(name = "MASIZE", nullable = false)
    private int masize;
    
    
    public CtpnId() {
    }

   
    public CtpnId(int mapn, int mamh, int masize) {
		super();
		this.mapn = mapn;
		this.mamh = mamh;
		this.masize = masize;
	}



	public int getMapn() {
        return this.mapn;
    }

    public void setMapn(int mapn) {
        this.mapn = mapn;
    }

    public int getMamh() {
        return this.mamh;
    }

    public void setMamh(int mamh) {
        this.mamh = mamh;
    }


	public int getMasize() {
		return masize;
	}


	public void setMasize(int masize) {
		this.masize = masize;
	}


	@Override
	public int hashCode() {
		return Objects.hash(mamh, mapn, masize);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CtpnId other = (CtpnId) obj;
		return mamh == other.mamh && mapn == other.mapn && masize == other.masize;
	}
    
    
    
}
