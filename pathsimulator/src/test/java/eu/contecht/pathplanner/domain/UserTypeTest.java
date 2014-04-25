package eu.contecht.pathplanner.domain;

import junit.framework.TestCase;

public class UserTypeTest extends TestCase{
	private UserType userType;
	
	protected void setUp() throws Exception  {
		userType = new UserType();
	}
	
	public void testSetAndGetName()  {
		String userName = "aName";
		assertNull(userType.getName());
		userType.setName(userName);
		assertEquals(userName, userType.getName());
	}
	
	
}
