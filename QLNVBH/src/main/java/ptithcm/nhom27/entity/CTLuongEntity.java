package ptithcm.nhom27.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "CT_LUONG")
public class CTLuongEntity {
	@EmbeddedId
	CTLUONGKey idctluong;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("manv")
	@JoinColumn(name = "MANV")
	private NhanVienEntity manvluong;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("bacluong")
	@JoinColumn( name = "BACLUONG")
	private LuongEntity bacluongentity;
	
	@Column(name = "LUONGTHUCNHAN")
	private int luongthucnhan;
	
	@Column(name = "PHUCAP")
	private int phucap;

	public CTLUONGKey getIdctluong() {
		return idctluong;
	}

	public void setIdctluong(CTLUONGKey idctluong) {
		this.idctluong = idctluong;
	}

	public NhanVienEntity getManvluong() {
		return manvluong;
	}

	public void setManvluong(NhanVienEntity manvluong) {
		this.manvluong = manvluong;
	}

	public LuongEntity getBacluongentity() {
		return bacluongentity;
	}

	public void setBacluongentity(LuongEntity bacluongentity) {
		this.bacluongentity = bacluongentity;
	}

	public int getLuongthucnhan() {
		return luongthucnhan;
	}

	public void setLuongthucnhan(int luongthucnhan) {
		this.luongthucnhan = luongthucnhan;
	}

	public int getPhucap() {
		return phucap;
	}

	public void setPhucap(int phucap) {
		this.phucap = phucap;
	}

	public CTLuongEntity(CTLUONGKey idctluong, NhanVienEntity manvluong, LuongEntity bacluongentity,
			int phucap, int luongthucnhan) {
		super();
		this.idctluong = idctluong;
		this.manvluong = manvluong;
		this.bacluongentity = bacluongentity;
		this.phucap = phucap;
		this.luongthucnhan = luongthucnhan;
	}

	public CTLuongEntity() {
		super();
	}
}
