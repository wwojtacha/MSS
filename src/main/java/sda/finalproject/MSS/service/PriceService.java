package sda.finalproject.MSS.service;

import org.springframework.stereotype.Service;
import sda.finalproject.MSS.model.FuelPrice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriceService {


    private FuelPrice fuelPrice = new FuelPrice();

    public FuelPrice downloadPrice() throws IOException {
        String priceString;

        List<String> urlList = new ArrayList<>();
        URL url = new URL("http://www.lotos.pl/144/poznaj_lotos/dla_biznesu/hurtowe_ceny_paliw");
        URLConnection yc = url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;
        while((inputLine = br.readLine()) != null) {

            //System.out.println(inputLine);
            urlList.add(inputLine);

        }
        br.close();

        int priceIndex = urlList.indexOf("\t\t<td>Olej napędowy EURODIESEL</td>") + 1;

        priceString = urlList.get(priceIndex).replace("\t", "").replace("<td>", "").replace(" ", "").replace("</td>", "").substring(0, 4);
        Float dieselPrice = Float.parseFloat(priceString) / 1000;

        fuelPrice.setFuelPrice(dieselPrice);
        return fuelPrice;
    }
}
