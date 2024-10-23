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
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String songId;

    @Column(nullable = false)
    private String songName;

    @Column(nullable = false)
    private String songArtist;

    @ManyToMany
    @Column(nullable = false)
    @JoinTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

}
