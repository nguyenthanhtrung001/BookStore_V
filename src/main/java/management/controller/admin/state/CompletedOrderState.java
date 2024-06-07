package management.controller.admin.state;

import management.entity.Phieudat;

public class CompletedOrderState implements OrderState{

	@Override
	public void handleOrder(Phieudat order, int trangthai) {
		// Đơn hàng đã hoàn thành không thể thay đổi trạng thái
	}

}
