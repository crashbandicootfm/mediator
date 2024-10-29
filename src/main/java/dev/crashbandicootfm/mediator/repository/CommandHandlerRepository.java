package dev.crashbandicootfm.mediator.repository;

import dev.crashbandicootfm.mediator.exception.CommandException;
import dev.crashbandicootfm.mediator.model.CommandHandler;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface CommandHandlerRepository {

  @NotNull <T, R> Optional<CommandHandler<T, R>> findCommandClassAndResponseClass(
      @NotNull Class<T> commandClass,
      @NotNull Class<R> responseClass) throws CommandException;
}
