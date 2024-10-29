package dev.crashbandicootfm.mediator.service;

import dev.crashbandicootfm.mediator.exception.CommandException;
import dev.crashbandicootfm.mediator.model.CommandHandler;
import dev.crashbandicootfm.mediator.repository.CommandHandlerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommandHandlerServiceImpl implements CommandHandlerService {

  @NotNull CommandHandlerRepository commandHandlerRepository;

  @Override
  public @NotNull <T, R> CommandHandler<T, R> getByCommandClassAndResponseClass(
      @NotNull Class<T> commandClass,
      @NotNull Class<R> responseClass) throws CommandException {
    return commandHandlerRepository.findCommandClassAndResponseClass(commandClass, responseClass)
        .orElseThrow(() -> new CommandException("No command handler found for class " + commandClass));
  }
}
