package L03.CNPM.Music.services.token;

import L03.CNPM.Music.DTOS.UserDTO;
import L03.CNPM.Music.components.LocalizationUtils;
import L03.CNPM.Music.exceptions.DataNotFoundException;
import L03.CNPM.Music.exceptions.PermissionDenyException;
import L03.CNPM.Music.models.Role;
import L03.CNPM.Music.models.User;
import L03.CNPM.Music.repositories.UserRepository;
import L03.CNPM.Music.repositories.RoleRepository;
import L03.CNPM.Music.utils.MessageKeys;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService{
    private final LocalizationUtils localizationUtils;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        if (!userDTO.getEmail().isBlank() && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DataIntegrityViolationException(
                    localizationUtils.getLocalizedMessage(MessageKeys.EMAIL_EXISTED));
        }

        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.ROLE_DOES_NOT_EXISTS)));
        if (role.getName().equalsIgnoreCase(Role.ADMIN)) {
            throw new PermissionDenyException("Registering admin accounts is not allowed");
        }

        String password = userDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        //convert from userDTO => user
        User newUser = User.builder()
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(encodedPassword)
                .country(userDTO.getCountry())
                .dateOfBirth(userDTO.getDateOfBirth())
                .dateOfBirth(userDTO.getDateOfBirth())
                .isActive(true)
                .role(role)
                .build();

        return userRepository.save(newUser);
    }
}
