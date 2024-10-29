package dev.crashbandicootfm.mediator.repository;

import dev.crashbandicootfm.mediator.exception.CommandException;
import dev.crashbandicootfm.mediator.model.CommandRunner;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface CommandRunnerRepository {

  @NotNull <T> Optional<CommandRunner<T>> findCommandClass(@NotNull Class<T> commandClass) throws CommandException;
}
