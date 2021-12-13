package hu.fulopmark.boatdatacollectorbe.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(schema = "boat_api")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class WindData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private double speed;

    @Column(nullable = false)
    private double direction;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    private Instant modDate = Instant.now();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WindData windData = (WindData) o;

        if (Double.compare(windData.speed, speed) != 0) return false;
        if (Double.compare(windData.direction, direction) != 0) return false;
        if (!id.equals(windData.id)) return false;
        if (!timestamp.equals(windData.timestamp)) return false;
        return modDate != null ? modDate.equals(windData.modDate) : windData.modDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        temp = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(direction);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + (modDate != null ? modDate.hashCode() : 0);
        return result;
    }
}
