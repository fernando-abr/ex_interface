package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Entre com os dados do contrato: ");
		System.out.print("Número: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Data (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.nextLine());
		System.out.print("Valor do contrato: ");
		double value = sc.nextDouble();
		sc.nextLine();
		System.out.print("Entre com o número de parcelas: ");
		int installmentNumber = sc.nextInt();
		sc.nextLine();
		Contract contract = new Contract(number, date, value);
		
		ContractService cs = new ContractService(new PaypalService());
		
		cs.processContract(contract, installmentNumber);
		
		for(Installment i : contract.getInstallments()) {
			System.out.printf("%s - %.2f\n", sdf.format(i.getDueDate()), i.getAmount());
		}
		

	}

}
