package management.entity;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CtsizeId implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int masize;
	private int mamh;

	public CtsizeId() {
	}

	public CtsizeId(int masize, int mamh) {

		this.masize = masize;
		this.mamh = mamh;
	}


	public int getMasize() {
		return masize;
	}

	public void setMasize(int masize) {
		this.masize = masize;
	}

	public int getMamh() {
		return this.mamh;
	}

	public void setMamh(int mamh) {
		this.mamh = mamh;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mamh, masize);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CtsizeId other = (CtsizeId) obj;
		return mamh == other.mamh && masize == other.masize;
	}

	

}

