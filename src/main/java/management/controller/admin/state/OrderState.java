package management.controller.admin.state;

import management.entity.Phieudat;

public interface OrderState {
	void handleOrder(Phieudat order, int trangthai);
	
}
