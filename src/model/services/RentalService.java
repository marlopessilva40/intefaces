package model.services;

import model.entities.CarRental;
import model.entities.Invoice;


public class RentalService {//servico de aluguel

	private Double pricePerDay; //preço por dia
	private Double pricePerHour; //preço por hora
	
	
	private TaxService taxService;

	//nao declarei o construtor padrao, isso torna obrigatoria informar
	public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
		
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}
	public void processInvoice(CarRental carrental) {
		long t1 = carrental.getStart().getTime();//get time pega o valor em milessegundos da data
		long t2 = carrental.getFinish().getTime();
		double hours = (double)(t2 - t1) /1000 /60 /60;
		//para converter milessegundo para minu /1000,  para converter minutos /60
		//para converter em horas /60
		
		double basicPayment;
		if (hours <= 12) {
			basicPayment = Math.ceil(hours) * pricePerHour;
		}
		else {
			basicPayment = Math.ceil(hours / 24) * pricePerDay;				
		}
	
		double tax = taxService.tax(basicPayment);
		
		carrental.setInvoice(new Invoice(basicPayment, tax));
	
}
	
	
	
}
