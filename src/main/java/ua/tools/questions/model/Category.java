package ua.tools.questions.model;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor
public class Category implements Writable {

    private List<Writable> explanations;

    private String info;

    public void addChild(Writable child){
        explanations.add(child);
    }
}
