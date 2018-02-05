package org.kalinga.Rest2.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kalinga.Rest2.Model.ErrorMessage;
@Provider
public class DnfExceptionMapper implements ExceptionMapper<DataNotFound>{

	@Override
	public Response toResponse(DataNotFound arg0) {
		ErrorMessage e=new ErrorMessage("Bro!! No Content!",9999);
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		
	}

	

}
