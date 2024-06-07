package management.controller.admin.state;

import management.entity.Phieudat;

public class OrderContext {
	private OrderState state;

    public OrderContext(OrderState state) {
        this.state = state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void changeState(Phieudat order, int trangthai) {
        state.handleOrder(order, trangthai);
    }
}
