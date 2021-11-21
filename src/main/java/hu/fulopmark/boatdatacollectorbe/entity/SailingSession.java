package hu.fulopmark.boatdatacollectorbe.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.xml.stream.Location;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

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
    private LocalDateTime end;


    @Column(nullable = false)
    private LocalDateTime inserted;

    @Column
    private String description;

    @OneToMany
    private Set<LocationPoint> locationPointSet;

    @OneToMany
    private Set<WindData> windDataSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SailingSession that = (SailingSession) o;

        if (!id.equals(that.id)) return false;
        if (!start.equals(that.start)) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        if (!inserted.equals(that.inserted)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + inserted.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
