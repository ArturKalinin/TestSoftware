package project.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MPA {
    private Integer mpaId;
    private String code;
    private String description;
}
