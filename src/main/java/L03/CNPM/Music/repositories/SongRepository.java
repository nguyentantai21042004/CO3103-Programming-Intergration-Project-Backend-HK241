package L03.CNPM.Music.repositories;

import L03.CNPM.Music.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
