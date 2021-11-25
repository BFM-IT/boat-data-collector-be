package hu.fulopmark.boatdatacollectorbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Distance {
    private int numOfPoints;
    private double distance;
}
