package hu.fulopmark.boatdatacollectorbe.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(schema = "boat_api")
@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class SailingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Instant startedAt;

    @Column
    private Instant endedAt;


    @Column(nullable = false)
    private Instant modDate = Instant.now();

    @Column
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SailingSession that = (SailingSession) o;

        if (!id.equals(that.id)) return false;
        if (!startedAt.equals(that.startedAt)) return false;
        if (!endedAt.equals(that.endedAt)) return false;
        if (!modDate.equals(that.modDate)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + startedAt.hashCode();
        result = 31 * result + endedAt.hashCode();
        result = 31 * result + modDate.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
