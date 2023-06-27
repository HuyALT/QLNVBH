package ptithcm.nhom27.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "CT_XUPHAT")
public class CT_XuphatEntity {
	
	@EmbeddedId
	private CT_Xuphatkey key;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("mucdo")
	@JoinColumn(name = "IDMD")
	private MucdoEntity idmd;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("manv")
	@JoinColumn(name = "MANV")
	private NhanVienEntity nventity;

	public CT_XuphatEntity(CT_Xuphatkey key, MucdoEntity idmd, NhanVienEntity nventity) {
		super();
		this.key = key;
		this.idmd = idmd;
		this.nventity = nventity;
	}

	public CT_XuphatEntity() {
		super();
	}



	public CT_Xuphatkey getKey() {
		return key;
	}



	public void setKey(CT_Xuphatkey key) {
		this.key = key;
	}



	public MucdoEntity getIdmd() {
		return idmd;
	}



	public void setIdmd(MucdoEntity idmd) {
		this.idmd = idmd;
	}

	public NhanVienEntity getNventity() {
		return nventity;
	}

	public void setNventity(NhanVienEntity nventity) {
		this.nventity = nventity;
	}
}
