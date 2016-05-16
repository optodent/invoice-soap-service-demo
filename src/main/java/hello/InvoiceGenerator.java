package hello;

import io.spring.guides.gs_producing_web_service.Invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class InvoiceGenerator {
	
	private Random rand = new Random();
	
	public Collection<Invoice> generateInvoices() {
		
		Random rand = new Random();
		int limit = rand.nextInt(10) + 1;
		List<Invoice> invoices = new ArrayList<>();
		for (int i = 0; i < limit; i++) {
			invoices.add(getInvoice());
		}

		return invoices;
	}
	
	private Invoice getInvoice() {
		Invoice invoice = new Invoice();
		invoice.setAddress(address());
		invoice.setDollars(dollars());
		invoice.setIban(iban());
		return invoice;
	}
	
	private BigDecimal dollars() {
        return new BigDecimal(1 + rand.nextInt(20_000));
    }

    private String address() {
        return "Test Street " + rand.nextInt(100) + 1;
    }

    private String iban() {
        return "test-iban-" + rand.nextInt(1000) + 1000;
    }
}
