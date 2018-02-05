package org.kalinga.Rest2.Test;


import static org.junit.Assert.*;

import java.util.List;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.kalinga.Rest2.Main;
import org.kalinga.Rest2.Model.Message;



public class TestRun{
	@Test
	public void testFetchingById() {
	   Main s = new Main();
	  
	   Message m=s.showOne(1);
	   assertEquals("shubham",m.getTo());
	}
	@Test
	public void testFetching() {
	   Main s = new Main();
	  
	   List<Message> m=s.show();
	   assertEquals("Prem",m.get(1).getFrom());
	}

}
