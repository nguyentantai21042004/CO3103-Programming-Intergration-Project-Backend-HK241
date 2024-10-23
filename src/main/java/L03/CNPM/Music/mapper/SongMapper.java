package L03.CNPM.Music.mapper;

import L03.CNPM.Music.DTOS.UploadSongDTO;
import L03.CNPM.Music.models.Genre;
import L03.CNPM.Music.models.Song;
import L03.CNPM.Music.responses.users.UploadSongResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song toSong(UploadSongDTO uploadSongDTO);

    @Mapping(target = "genres", ignore = true)
    UploadSongResponse toUploadSongResponse(Song song);

    // Add this method to map List<String> to List<Genre>
    default List<Genre> map(List<String> genreNames) {
        return genreNames.stream()
                .map(Genre::new)
                .collect(Collectors.toList());
    }
}
