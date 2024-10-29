package dev.crashbandicootfm.mediator.service;

import dev.crashbandicootfm.mediator.exception.CommandException;
import dev.crashbandicootfm.mediator.model.CommandRunner;
import org.jetbrains.annotations.NotNull;

public interface CommandRunnerService {

  @NotNull <T> CommandRunner<T> getByCommandClass(@NotNull Class<T> commandClass) throws CommandException;
}
