package dev.crashbandicootfm.mediator.service;

import dev.crashbandicootfm.mediator.exception.CommandException;
import dev.crashbandicootfm.mediator.model.CommandRunner;
import dev.crashbandicootfm.mediator.repository.CommandRunnerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommandRunnerServiceImpl implements CommandRunnerService {

  @NotNull CommandRunnerRepository commandRunnerRepository;

  @Override
  public @NotNull <T> CommandRunner<T> getByCommandClass(@NotNull Class<T> commandClass) throws CommandException {
    return commandRunnerRepository.findCommandClass(commandClass)
        .orElseThrow(() -> new CommandException("No command runner found for runner %s".formatted(commandClass)));
  }
}
