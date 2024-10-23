package L03.CNPM.Music.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @Column(nullable = false)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private List<Song> songs;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

}
