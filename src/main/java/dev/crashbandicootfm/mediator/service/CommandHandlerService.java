package dev.crashbandicootfm.mediator.service;

import dev.crashbandicootfm.mediator.exception.CommandException;
import dev.crashbandicootfm.mediator.model.CommandHandler;
import org.jetbrains.annotations.NotNull;

public interface CommandHandlerService {

  @NotNull <T, R> CommandHandler<T, R> getByCommandClassAndResponseClass(
      @NotNull Class<T> commandClass,
      @NotNull Class<R> responseClass) throws CommandException;
}
