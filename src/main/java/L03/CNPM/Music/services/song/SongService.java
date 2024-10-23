package L03.CNPM.Music.services.song;


import L03.CNPM.Music.DTOS.UploadSongDTO;
import L03.CNPM.Music.mapper.SongMapper;
import L03.CNPM.Music.models.Genre;
import L03.CNPM.Music.repositories.GenreRepository;
import L03.CNPM.Music.repositories.SongRepository;
import L03.CNPM.Music.responses.users.UploadSongResponse;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SongService {
    SongRepository songRepository;
    GenreRepository genreRepository;
    SongMapper songMapper;

    @Transactional
    public UploadSongResponse uploadSong(UploadSongDTO upload) {
        var song = songMapper.toSong(upload);
        var genreList = genreRepository.findByGenreNameIn(upload.getGenres());
        song.setGenres(genreList);
        songRepository.save(song);

        var response = songMapper.toUploadSongResponse(song);
        response.setGenres(upload.getGenres());
        return response;
    }

}
