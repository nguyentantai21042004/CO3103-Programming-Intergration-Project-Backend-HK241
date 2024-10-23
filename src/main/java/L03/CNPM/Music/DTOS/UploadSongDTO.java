package L03.CNPM.Music.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Getter
@Setter
public class UploadSongDTO {
    @JsonProperty("songName")
    private String songName;

    @JsonProperty("songArtist")
    private String songArtist;

    @JsonProperty("genres")
    private List<String> genres;
}
