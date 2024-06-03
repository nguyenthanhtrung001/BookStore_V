package management.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ctsize")
public class Ctsize implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "masize", column = @Column(name = "MASIZE", nullable = false)),
        @AttributeOverride(name = "mamh", column = @Column(name = "MAMH", nullable = false))
    })
    private CtsizeId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MASIZE", nullable = false, insertable = false, updatable = false)
    private Size size;

    public Set<Cthd> getCthds() {
		return cthds;
	}

	public void setCthds(Set<Cthd> cthds) {
		this.cthds = cthds;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MAMH", nullable = false, insertable = false, updatable = false)
    private Mathang mathang;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ctsize")
    private Set<Ctpd> ctpds = new HashSet<Ctpd>(0);
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ctsize")
    private Set<Ctpn> ctpns = new HashSet<Ctpn>(0);
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ctsize")
    private Set<Cthd> cthds = new HashSet<Cthd>(0);
    
    @Column(name = "SOLUONG")
    private int soluong;

    public Ctsize() {
    }

    public Ctsize(CtsizeId id, Size size, Mathang mathang) {
        this.id = id;
        this.size = size;
        this.mathang = mathang;
    }

    public CtsizeId getId() {
        return id;
    }

    public void setId(CtsizeId id) {
        this.id = id;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Mathang getMathang() {
        return mathang;
    }

    public void setMathang(Mathang mathang) {
        this.mathang = mathang;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    
	public Set<Ctpd> getCtpds() {
		return ctpds;
	}

	public void setCtpds(Set<Ctpd> ctpds) {
		this.ctpds = ctpds;
	}

	public Set<Ctpn> getCtpns() {
		return ctpns;
	}

	public void setCtpns(Set<Ctpn> ctpns) {
		this.ctpns = ctpns;
	}

	@Override
	public String toString() {
		return "Ctsize [id=" + id + ", size=" + size + ", mathang=" + mathang + ", ctpds=" + ctpds + ", ctpns=" + ctpns
				+ ", cthds=" + cthds + ", soluong=" + soluong + "]";
	}

	
}
