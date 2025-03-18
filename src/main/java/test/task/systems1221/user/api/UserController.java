package test.task.systems1221.user.api;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import test.task.systems1221.user.dal.UserService;
import test.task.systems1221.user.dto.UserCreateDto;
import test.task.systems1221.user.dto.UserDto;
import test.task.systems1221.user.dto.UserUpdateDto;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public PagedModel<UserDto> getAll(Pageable pageable) {
        Page<UserDto> userDtos = userService.getAll(pageable);
        return new PagedModel<>(userDtos);
    }

    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable UUID id) {
        return userService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<UserDto> getMany(@RequestParam List<UUID> ids) {
        return userService.getMany(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Validated UserCreateDto dto) {
        return userService.create(dto);
    }

    @PatchMapping("/{id}")
    public UserDto patch(@PathVariable UUID id, @RequestBody @Validated UserUpdateDto userUpdateDto) {
        return userService.patch(id, userUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserDto delete(@PathVariable UUID id) {
        return userService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMany(@RequestParam List<UUID> ids) {
        userService.deleteMany(ids);
    }
}
