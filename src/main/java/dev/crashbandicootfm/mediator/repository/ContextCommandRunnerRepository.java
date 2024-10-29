package dev.crashbandicootfm.mediator.repository;

import dev.crashbandicootfm.mediator.exception.CommandException;
import dev.crashbandicootfm.mediator.model.CommandRunner;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContextCommandRunnerRepository implements CommandRunnerRepository {

  @NotNull ApplicationContext applicationContext;

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <T> Optional<CommandRunner<T>> findCommandClass(@NotNull Class<T> commandClass) throws CommandException {
    ResolvableType resolvableType = ResolvableType.forClassWithGenerics(CommandRunner.class, commandClass);
    String[] beanNames = applicationContext.getBeanNamesForType(resolvableType);
    if (beanNames.length > 1) {
      throw new CommandException(String.format("Multiple command runners found for %s", commandClass));
    }
    return Optional.ofNullable((CommandRunner<T>) applicationContext.getBeanProvider(resolvableType).getIfAvailable());
  }
}
