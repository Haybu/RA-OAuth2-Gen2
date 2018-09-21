package io.springframework.flights;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataSeed {

    private final String filePath;

    public DataSeed(String filepath) {
        this.filePath = filepath;
    }


    public List<Flight> read() throws ParseException {
        Resource resource = new ClassPathResource(filePath);

        List<Flight> flights = new ArrayList<>();

        try{
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            Flight flight;
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(":");
                flight = new Flight();
                flight.setNbr(fields[0]);
                flight.setAirline(fields[1]);
                flight.setOrigin(fields[2]);
                flight.setDestination(fields[3]);
                flight.setStops(Integer.valueOf(fields[4]));
                flight.setPrice(Double.valueOf(fields[5]));
                flight.setCapacity(Integer.valueOf(fields[6]));
                flight.setPlane(fields[7]);
                flight.setDeparture(toDate(fields[8]));
                flights.add(flight);
            }
            br.close();

        } catch(IOException e){
            e.printStackTrace();
        }

        return flights;
    }

    private LocalDate toDate(String str) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(str, formatter);
    }

}
