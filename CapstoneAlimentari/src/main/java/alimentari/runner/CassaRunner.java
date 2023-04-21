package alimentari.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import alimentari.entity.Cassa;
import alimentari.service.CassaService;

@Component
@Order(3)
public class CassaRunner implements ApplicationRunner {
	
	@Autowired CassaService cassaServ;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("cassa.exe");
//		setCasse();
		
	}
	
	private void setCasse() {
		Cassa c;
		for (int i=1; i<=6; i++) {
			c= new Cassa();
			c.setFondoCassa(100.0);
			c.setNumeroCassa(i);
			c.setCassaFunzionante(true);
			cassaServ.registraCassa(c);
		}
	}

}
