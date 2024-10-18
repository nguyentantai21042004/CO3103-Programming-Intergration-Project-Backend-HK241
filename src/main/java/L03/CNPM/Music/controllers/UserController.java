package L03.CNPM.Music.controllers;

import L03.CNPM.Music.DTOS.UserDTO;
import L03.CNPM.Music.components.LocalizationUtils;
import L03.CNPM.Music.models.User;
import L03.CNPM.Music.responses.ResponseObject;
import L03.CNPM.Music.responses.users.UserResponse;
import L03.CNPM.Music.services.token.IUserService;
import L03.CNPM.Music.utils.MessageKeys;
import L03.CNPM.Music.utils.ValidationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final LocalizationUtils localizationUtils;
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser (
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) throws Exception {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .message(errorMessages.toString())
                    .build());
        }

        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isBlank()) {
                return ResponseEntity.badRequest().body(ResponseObject.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .data(null)
                        .message("Email is required")
                        .build());
        } else {
            //Email not blank
            if (!ValidationUtils.isValidEmail(userDTO.getEmail())) {
                throw new Exception("Invalid email format");
            }
        }

        if (!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
            //registerResponse.setMessage();
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .message(localizationUtils.getLocalizedMessage(MessageKeys.PASSWORD_NOT_MATCH))
                    .build());
        }

        User newUser = userService.createUser(userDTO);
        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.CREATED)
                .data(UserResponse.fromUser(newUser))
                .message(MessageKeys.REGISTER_SUCCESSFULLY)
                .build());
    }
}