package org.kalinga.Rest2.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kalinga.Rest2.Model.ErrorMessage;
@Provider
public class AllException implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable arg0) {
		ErrorMessage e=new ErrorMessage("Something went wrong!! Check again",8998);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		
	}

}
