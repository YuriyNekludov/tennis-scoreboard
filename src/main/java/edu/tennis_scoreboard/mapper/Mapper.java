package edu.tennis_scoreboard.mapper;

public interface Mapper<T, S> {

    S mapFrom(T object);
}
