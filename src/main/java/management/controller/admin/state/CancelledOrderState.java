package management.controller.admin.state;

import management.entity.Phieudat;

public class CancelledOrderState implements OrderState{

	@Override
	public void handleOrder(Phieudat order, int trangthai) {
		if (trangthai != 4) {
            order.setTrangthai(trangthai);
        }
	}

}
