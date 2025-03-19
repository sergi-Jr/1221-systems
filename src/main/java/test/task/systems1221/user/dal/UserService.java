package test.task.systems1221.user.dal;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.task.systems1221.exception.ResourceNotFoundException;
import test.task.systems1221.user.dto.UserCreateDto;
import test.task.systems1221.user.dto.UserDto;
import test.task.systems1221.user.dto.UserUpdateDto;
import test.task.systems1221.user.mapper.UserMapper;
import test.task.systems1221.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public Page<UserDto> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toUserDto);
    }

    public UserDto getOne(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userMapper.toUserDto(userOptional.orElseThrow(() ->
                new ResourceNotFoundException("Entity with id `%s` not found".formatted(id))));
    }

    public List<UserDto> getMany(List<UUID> ids) {
        List<User> users = userRepository.findAllById(ids);
        return users.stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @Transactional
    public UserDto create(UserCreateDto dto) {
        User user = userMapper.toEntity(dto);
        User resultUser = userRepository.save(user);
        return userMapper.toUserDto(resultUser);
    }

    @Transactional
    public UserDto patch(UUID id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity with id `%s` not found".formatted(id)));

        userMapper.updateWithNull(userUpdateDto, user);

        User resultUser = userRepository.save(user);
        return userMapper.toUserDto(resultUser);
    }

    @Transactional
    public UserDto delete(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return userMapper.toUserDto(user);
    }

    @Transactional
    public void deleteMany(List<UUID> ids) {
        userRepository.deleteAllById(ids);
    }
}
