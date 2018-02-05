package org.kalinga.Rest2;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Status;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.Uri;
import org.hibernate.Query;
import org.hibernate.Session;
import org.kalinga.Rest2.Exception.DataNotFound;
import org.kalinga.Rest2.Model.Message;
import org.kalinga.Rest2.Model.hibernateUtility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/messages")
public class Main {
	
	@GET
	@Produces("application/json")
	public List<Message> show(){
		List<Message> lm=new ArrayList<Message>();
		Session session=org.kalinga.Rest2.Model.hibernateUtility.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery("FROM Message");
		List l=q.list();
		Iterator i=l.iterator();
		while(i.hasNext()){
			
			Message m=(Message)i.next();
			Message x=new Message();
			x.setId(m.getId());
			x.setFrom(m.getFrom());
			x.setTo(m.getTo());
			x.setMsg(m.getMsg());

			lm.add(x);
			
			System.out.println(m.getId()+"    "+m.getFrom()+"   ");
			
			
		}	
		  Gson gson = new GsonBuilder().create();
	      gson.toJson(lm);
		session.getTransaction().commit();
		session.close();
		return lm;
		
	}
	
	@Path("/query")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Posting(
			@QueryParam("id") int id,
			@QueryParam("fromm") String from,
			@QueryParam("too") String to,
			@QueryParam("msg") String str,@Context UriInfo uri){
		
		Session s=hibernateUtility.getSessionFactory().openSession();
		s.beginTransaction();
	//	Message me=(Message) s.load(Message.class,id);
		//if(me!=null){throw new DataNotFound("same id");}
		Message m=new Message();
		m.setId(id);
		m.setFrom(from);
		m.setMsg(str);
		m.setTo(to);
		
		s.save(m);
		
		String newid=String.valueOf(m.getId());
		URI Urii=uri.getAbsolutePathBuilder().path(newid).build();
	
		s.getTransaction().commit();
		s.close();
		
		
		
		return Response.created(Urii).entity(m).build();

	}
	
	@Path("/putting/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Putting(@QueryParam("msg") String mess,@PathParam("id") int idd,@Context UriInfo uri){
	
		
		Session s=hibernateUtility.getSessionFactory().openSession();
		s.beginTransaction();
		Message me=(Message)s.load(Message.class,new Integer(idd));
		if(me==null){throw new DataNotFound("no Content");}
		me.setMsg(mess);
		System.out.println("check for setting  "+mess);
		s.update(me);
		String newid=String.valueOf(idd);
		URI urii=uri.getAbsolutePath();
		s.getTransaction().commit();
		s.close();
		
		String str="done";
		return Response.created(urii).build();
		
		
	}
	@GET
	@Produces("application/json")
	@Path("/{id}")
	public Message showOne(@PathParam("id") int id){
		
		Session session=hibernateUtility.getSessionFactory().openSession();
		session.beginTransaction();
	
		Query q=session.createQuery("FROM Message where id=:idd");
		q.setInteger("idd",id);
		Message m=(Message)q.uniqueResult();
		if(m==null){
			throw new DataNotFound("BROTHER!!! NO CONTENT");
		}
	
//		List l=q.list();
//		Iterator i=l.iterator();
//		while(i.hasNext()){
//			
//			Message m=(Message)i.next();
//		
//
//			if(m.getId()==id){
//				return m;
//			}
//			
//			System.out.println(m.getId()+"    "+m.getFrom()+"   ");
//			
//			
//		}	
		 Gson gson = new GsonBuilder().create();
	        gson.toJson(m);
		return m;
	}
	
	

	
}
