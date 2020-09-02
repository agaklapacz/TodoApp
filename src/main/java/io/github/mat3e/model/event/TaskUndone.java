package io.github.mat3e.model.event;

import io.github.mat3e.model.Task;

import java.time.Clock;

public class TaskUndone extends TaskEvent{
    TaskUndone(final Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
