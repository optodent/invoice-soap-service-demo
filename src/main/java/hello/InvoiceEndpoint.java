package hello;

import io.spring.guides.gs_producing_web_service.GetInvoiceRequest;
import io.spring.guides.gs_producing_web_service.GetInvoiceResponse;
import io.spring.guides.gs_producing_web_service.Invoice;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class InvoiceEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private InvoiceGenerator invoiceGenerator;

	@Autowired
	public InvoiceEndpoint(InvoiceGenerator invoiceGenerator) {
		this.invoiceGenerator = invoiceGenerator;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInvoiceRequest")
	@ResponsePayload
	public GetInvoiceResponse getInvoices(@RequestPayload GetInvoiceRequest request) {
		
		GetInvoiceResponse response = new GetInvoiceResponse();
		Collection<Invoice> generateInvoices = invoiceGenerator.generateInvoices();
		
		for (Invoice invoice : generateInvoices) {
			response.getInvoice().add(invoice);
		}
		
		return response;
	}
}
