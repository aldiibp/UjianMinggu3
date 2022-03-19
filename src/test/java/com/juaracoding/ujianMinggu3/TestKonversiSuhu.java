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
public class TestKonversiSuhu {

	static final Logger log = getLogger(lookup().lookupClass());
	KonversiSuhu suhu;
	
	@Before
	public void setUp() throws Exception {
		suhu = new KonversiSuhu();
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
	@FileParameters("src/test/resources/FkeC.csv")
	public void testKonversiSuhu(int f, double expect) {
		log.debug("test Konversi Suhu");
		assertEquals(expect, suhu.KonversiSuhu(f), 0.9);
	}

	@Test
	public void testKonversiSuhuBukanAngka() {
		log.debug("test Konversi Suhu Bukan Angka");
		String fBukanAngka = "A";
		
		double convertF = Double.parseDouble(fBukanAngka);
		
		double actual = suhu.KonversiSuhu((int) convertF);
		String expect = "B";
		
		assertEquals(Double.parseDouble(expect), actual, 0.0);
		fail();
	}
	
	@Test
	public void testKonversiSuhuAngkaNegatif() {
		log.debug("test Konversi Suhu Angka Negatif");
		int f = -4;
		
		double actual = suhu.KonversiSuhu(f);
		assertEquals(-20, actual, 0.0);
	}
	
	@Test
	public void testKonversiSuhuNull() {
		log.debug("test Konversi Suhu Null");
		int f = 32;
		Double fNull = null;
		
		double actual = suhu.KonversiSuhu(f);
		
		assertNull(fNull);
		
		assertEquals(0, actual, 0.0);
	}
}
