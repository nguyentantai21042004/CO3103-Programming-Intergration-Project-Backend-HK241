package L03.CNPM.Music.services.token;

import L03.CNPM.Music.DTOS.UserDTO;
import L03.CNPM.Music.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
}
