package utils.create_id.random_id;

import java.util.function.Function;

public interface IRandomID {
    public Function<Integer, Integer> randomID = x -> (int) (Math.random() * Math.pow(10, x - 1) + Math.pow(10, x - 1));
}