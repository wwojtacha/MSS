package sda.finalproject.MSS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sda.finalproject.MSS.model.FuelPrice;
import sda.finalproject.MSS.service.FuelService;
import sda.finalproject.MSS.service.PriceService;

import java.io.IOException;

@SpringBootApplication
public class MssApplication {

	public static void main(String[] args) {
		SpringApplication.run(MssApplication.class, args);


	}
}
