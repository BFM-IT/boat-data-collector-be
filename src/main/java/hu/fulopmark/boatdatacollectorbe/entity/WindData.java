package hu.fulopmark.boatdatacollectorbe.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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
    private LocalDateTime timestamp;

    @Column
    private LocalDateTime inserted;

    @ManyToOne(optional = false)
    private SailingSession sailingSession;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WindData windData = (WindData) o;

        if (Double.compare(windData.speed, speed) != 0) return false;
        if (Double.compare(windData.direction, direction) != 0) return false;
        if (!id.equals(windData.id)) return false;
        if (!timestamp.equals(windData.timestamp)) return false;
        if (inserted != null ? !inserted.equals(windData.inserted) : windData.inserted != null) return false;
        return sailingSession.equals(windData.sailingSession);
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
        result = 31 * result + (inserted != null ? inserted.hashCode() : 0);
        result = 31 * result + sailingSession.hashCode();
        return result;
    }
}
