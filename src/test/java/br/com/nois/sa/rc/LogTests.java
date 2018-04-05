package br.com.nois.sa.rc;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.impl.LogControllerImpl;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Util;

public class LogTests extends ApplicationTests {

	@Autowired
	LogRepository logRepository;

	@Autowired
	VersaoRepository versaoRepository;

	@Autowired
	LogController logController;

	@Before
	public void setUp() {
		this.logController = new LogControllerImpl(this.logRepository, this.versaoRepository);
	}

	@Test
	@Ignore
	public void Insert() {
		String Method = "Insert";
		try {
			Log log = new Log("funcionalidade", "mudanca");
			log = this.logController.insert(log);
			assertNotNull(log);
			new Util().printTest(Method, "inserts", log);

		} catch (Exception ex) {
			assertNotEquals(ex.getMessage(), "ERRO");
		}
	}
}
