package management.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import management.DTO.ProductDTO;

@Entity
@DiscriminatorValue("Default")
public class DefaultMathang extends Mathang {

	public DefaultMathang(ProductDTO pro) {
		super(pro.getTenSPMoi(), pro.getMoTa(), 1);

		Nhanhieu nhanHieu = new Nhanhieu();

		// cần sửa

		nhanHieu.setManh(Integer.parseInt(pro.getNhanHieu()));

		super.setNhanhieu(nhanHieu);
	}

	public DefaultMathang() {
	}

}