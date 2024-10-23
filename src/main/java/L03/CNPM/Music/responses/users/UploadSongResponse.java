package L03.CNPM.Music.responses.users;

import L03.CNPM.Music.models.Role;
import L03.CNPM.Music.models.Song;
import L03.CNPM.Music.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadSongResponse {
    @JsonProperty("songName")
    private String songName;

    @JsonProperty("songArtist")
    private String songArtist;

    @JsonProperty("genres")
    private List<String> genres;
}
