package hu.fulopmark.boatdatacollectorbe.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
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
    private LocalDateTime start;

    @Column
    private Instant end;


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
        if (!start.equals(that.start)) return false;
        if (!end.equals(that.end)) return false;
        if (!modDate.equals(that.modDate)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + modDate.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
