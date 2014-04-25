package eu.contecht.pathplanner.domain;

import desmoj.core.dist.ContDist;
import desmoj.core.dist.ContDistUniform;
import junit.framework.TestCase;

public class ServiceTest extends TestCase {
		private Service service;
		
		private static int USER_COUNT = 2;
		
		protected void setUp() throws Exception  {
			service = new Service(null, null, false);
		}
		
		public void testSetAndGetName()  {
			String serviceName = "aService";
			assertNull(service.getName());
			service.setName(serviceName);
			assertEquals(serviceName, service.getName());
		}

		public void testSetAndGetServiceTime()  {
			ContDist serviceTime = new ContDistUniform(null, "TestServiceTime", 3.0, 7.0, true, false);
			assertNull(service.getServiceTime());
			service.setServiceTime(serviceTime);
			assertEquals(serviceTime, service.getServiceTime());
		}
		
}
