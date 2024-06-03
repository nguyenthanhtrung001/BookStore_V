package management.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cthd")
public class Cthd implements java.io.Serializable {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "sohd", column = @Column(name = "SOHD", nullable = false)),
            @AttributeOverride(name = "masize", column = @Column(name = "MASIZE", nullable = false)),
            @AttributeOverride(name = "mamh", column = @Column(name = "MAMH", nullable = false))
    })
    private CthdId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SOHD", nullable = false, insertable = false, updatable = false)
    private Hoadon hoadon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name = "MAMH", referencedColumnName = "MAMH", insertable = false, updatable = false),
        @JoinColumn(name = "MASIZE", referencedColumnName = "MASIZE", insertable = false, updatable = false)
    }) 	
    private Ctsize ctsize;

    @Column(name = "SOLUONG", nullable = false)
    private int soluong;

    public Cthd() {
    }

	public Cthd(CthdId id, Hoadon hoadon, Ctsize ctsize, int soluong) {
		super();
		this.id = id;
		this.hoadon = hoadon;
		this.ctsize = ctsize;
		this.soluong = soluong;
	}

	public CthdId getId() {
		return id;
	}

	public void setId(CthdId id) {
		this.id = id;
	}

	public Hoadon getHoadon() {
		return hoadon;
	}

	public void setHoadon(Hoadon hoadon) {
		this.hoadon = hoadon;
	}

	public Ctsize getCtsize() {
		return ctsize;
	}

	public void setCtsize(Ctsize ctsize) {
		this.ctsize = ctsize;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

    
    
    
}
  