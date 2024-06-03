package management.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CthdId implements java.io.Serializable {

    @Column(name = "SOHD", nullable = false)
    private int sohd;

    @Column(name = "MAMH", nullable = false)
    private int mamh;

    @Column(name = "MASIZE", nullable = false)
    private int masize;

    
    public CthdId() {
    }


	public CthdId(int sohd, int mamh, int masize) {
		super();
		this.sohd = sohd;
		this.mamh = mamh;
		this.masize = masize;
	}


	public int getSohd() {
		return sohd;
	}


	public void setSohd(int sohd) {
		this.sohd = sohd;
	}


	public int getMamh() {
		return mamh;
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
		return Objects.hash(mamh, masize, sohd);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CthdId other = (CthdId) obj;
		return mamh == other.mamh && masize == other.masize && sohd == other.sohd;
	}

    
    
}
