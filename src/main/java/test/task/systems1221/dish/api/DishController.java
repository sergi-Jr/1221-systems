package test.task.systems1221.dish.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import test.task.systems1221.dish.dal.DishService;
import test.task.systems1221.dish.dto.DishCreateDto;
import test.task.systems1221.dish.dto.DishDto;
import test.task.systems1221.dish.dto.DishUpdateDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public PagedModel<DishDto> getAll(Pageable pageable) {
        Page<DishDto> dishes = dishService.getAll(pageable);
        return new PagedModel<>(dishes);
    }

    @GetMapping("/{id}")
    public DishDto getOne(@PathVariable UUID id) {
        return dishService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<DishDto> getMany(@RequestParam List<UUID> ids) {
        return dishService.getMany(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DishDto create(@RequestBody DishCreateDto dto) {
        return dishService.create(dto);
    }

    @PatchMapping("/{id}")
    public DishDto patch(@PathVariable UUID id, @RequestBody DishUpdateDto updateDto) {
        return dishService.patch(id, updateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        dishService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMany(@RequestParam List<UUID> ids) {
        dishService.deleteMany(ids);
    }
}
