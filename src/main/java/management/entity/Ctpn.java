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
@Table(name = "ctpn")
public class Ctpn implements java.io.Serializable {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "mapn", column = @Column(name = "MAPN", nullable = false)),
            @AttributeOverride(name = "masize", column = @Column(name = "MASIZE", nullable = false)),
            @AttributeOverride(name = "mamh", column = @Column(name = "MAMH", nullable = false))
    })
    private CtpnId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name = "MAMH", referencedColumnName = "MAMH", insertable = false, updatable = false),
        @JoinColumn(name = "MASIZE", referencedColumnName = "MASIZE", insertable = false, updatable = false)
    }) 	
    private Ctsize ctsize;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MAPN", nullable = false, insertable = false, updatable = false)
    private Phieunhap phieunhap;

    @Column(name = "SOLUONG", nullable = false)
    private int soluong;

    @Column(name = "DONGIA", nullable = false)
    private int dongia;

    public Ctpn() {
    }

   

    public Ctpn(CtpnId id, Ctsize ctsize, Phieunhap phieunhap, int soluong, int dongia) {
		super();
		this.id = id;
		this.ctsize = ctsize;
		this.phieunhap = phieunhap;
		this.soluong = soluong;
		this.dongia = dongia;
	}

	public Phieunhap getPhieunhap() {
        return this.phieunhap;
    }

    public void setPhieunhap(Phieunhap phieunhap) {
        this.phieunhap = phieunhap;
    }
    

	public CtpnId getId() {
        return this.id;
    }

    public void setId(CtpnId id) {
        this.id = id;
    }

   
    public Ctsize getCtsize() {
		return ctsize;
	}



	public void setCtsize(Ctsize ctsize) {
		this.ctsize = ctsize;
	}


    public int getSoluong() {
        return this.soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongia() {
        return this.dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }
}
