package uz.pdp.esg.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestResult {
    private List<Long> resultIds = new ArrayList<>();
    private Integer resultCount;
}
