package ua.tools.questions.model;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class Explanation implements Writable{
    private String info;
}
