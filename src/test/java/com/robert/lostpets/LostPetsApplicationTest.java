package com.robert.lostpets;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Clase test para la clase LostPetsApplication.
 *
 * @author Robert Ene
 * @see com.robert.lostpets.LostPetsApplication
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class LostPetsApplicationTest {

	@Autowired
	private LostPetsApplication lostPetsApplication;

	@Test
	public void testLostPetsApplication() throws Exception {
		assertNotNull(lostPetsApplication);
	}
}