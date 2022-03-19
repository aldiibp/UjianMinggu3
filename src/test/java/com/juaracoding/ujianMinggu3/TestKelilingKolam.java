package com.juaracoding.ujianMinggu3;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.Assert.*;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestKelilingKolam {
	
	static final Logger log = getLogger(lookup().lookupClass());
	Kolam kolam;
	
	@Before
	public void setUp() throws Exception {
		kolam = new Kolam();
	}

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
	
		@Override
		protected void succeeded(Description description) {
			log.debug("Pesan Sukses: {}", description.getMethodName());
		}
		
		@Override
		protected void failed(Throwable e, Description description) {
			log.debug("Pesan Failed: {}", description.getMethodName());
		}
	};
	
	@Test
	@FileParameters("src/test/resources/KelilingKolam.csv")
	public void testKelilingKolam(double p, double l, double t, double expect) {
		log.debug("test Keliling Kolam");
		assertEquals(expect, kolam.kelilingKolam(p, l, t), 0.0);
	}
	
	@Test
	public void testKelilingKolamBukanAngka() {
		log.debug("test Keliling Kolam Bukan Angka");
		String pBukanAngka = "A";
		String lBukanAngka = "B";
		String tBukanAngka = "C";
		
		double convertP = Double.parseDouble(pBukanAngka);
		double convertL = Double.parseDouble(lBukanAngka);
		double convertT = Double.parseDouble(tBukanAngka);
		
		double actual = kolam.kelilingKolam(convertP, convertL, convertT);
		String expect = "ABC";
		
		assertEquals(Double.parseDouble(expect), actual, 0.0);
		fail();
	}
	
	@Test
	public void testKelilingKolamAngkaNegatif() {
		log.debug("test Keliling Kolam Angka Negatif");
		double p = 12;
		double l = -3;
		double t = -5;
		
		double actual = kolam.kelilingKolam(p, l, t);
		assertEquals(16, actual, 0.0);
	}
	
	@Test
	public void testKelilingKolamNull() {
		log.debug("test Keliling Kolam Null");
		double p = 0;
		double l = 0;
		double t = 0;
		Double pNull = null;
		Double lNull = null;
		Double tNull = null;
		
		double actual = kolam.kelilingKolam(p, l, t);
		
		assertNull(pNull);
		assertNull(lNull);
		assertNull(tNull);
		
		assertEquals(0, actual, 0.0);
	}
}
