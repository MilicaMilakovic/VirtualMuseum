package net.etfbl.ip.vm.payment.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate;

import net.etfbl.ip.vm.dto.Ticket;
import net.etfbl.ip.vm.payment.model.PaymentDetails;
import net.etfbl.ip.vm.payment.service.PaymentService;

@Path("/payment")
public class PaymentApi {

	private static PaymentService service = new PaymentService();
	public PaymentApi() {
		// TODO Auto-generated constructor stub
	}

	@GET	
	public Response test() {		
		return Response.status(200).entity("OK").build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response pay(PaymentDetails payment) {
//		System.out.println(payment);
		try {
			Ticket ticket = service.buyTicket(payment);
			if(ticket!=null)
				return Response.status(200).header("Access-Control-Allow-Origin","*").entity(ticket).build();
		} catch(Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		} 
		return Response.status(500).entity("Error").build();
	}
}
