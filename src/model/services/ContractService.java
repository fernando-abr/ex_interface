package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService paymentService;
	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public OnlinePaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	

public void processContract(Contract contract, Integer months) {
    double baseValue = contract.getTotalValue() / months;

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(contract.getDate());

    for (int i = 0; i < months; i++) {
        double totalValue = baseValue + paymentService.interest(baseValue, i+1);
        
        calendar.add(Calendar.MONTH, 1);
        Date installmentDate = calendar.getTime();

        Installment installment = new Installment(installmentDate, paymentService.paymentFee(totalValue));
        contract.getInstallments().add(installment);
    }
}
}
