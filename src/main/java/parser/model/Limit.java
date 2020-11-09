package parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Limit {
    private final int low;
    private final int high;
}
