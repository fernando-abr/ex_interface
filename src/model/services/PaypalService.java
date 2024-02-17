package model.services;

public class PaypalService implements OnlinePaymentService{
	
	public PaypalService() {
	}
	
	public Double paymentFee(Double amount) {
		amount += amount * 0.02;
		return amount;
	}
	
	public Double interest(Double amount, Integer months) {
		return amount * 0.01 * months;
	}

}
