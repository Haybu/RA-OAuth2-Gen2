package io.springframework.flights;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flight {

    @Id
    private ObjectId id;

    private String nbr;

    private String airline;

    private String origin;

    private String destination;

    private Integer stops;

    private Double price;

    private Integer capacity;

    private String plane;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate departure;

    public String getId() {
        return id.toString();
    }
    public void setId(String id) {
        this.id = new ObjectId(id);
    }

}
