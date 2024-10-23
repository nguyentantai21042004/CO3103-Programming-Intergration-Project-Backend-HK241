package L03.CNPM.Music.repositories;

import L03.CNPM.Music.models.Genre;
import L03.CNPM.Music.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findByGenreNameIn(List<String> genreNames);
}
