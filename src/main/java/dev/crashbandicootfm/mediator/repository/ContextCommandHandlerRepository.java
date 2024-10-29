package dev.crashbandicootfm.mediator.repository;

import dev.crashbandicootfm.mediator.exception.CommandException;
import dev.crashbandicootfm.mediator.model.CommandHandler;
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
public class ContextCommandHandlerRepository implements CommandHandlerRepository {

  @NotNull ApplicationContext applicationContext;

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <T, R> Optional<CommandHandler<T, R>> findCommandClassAndResponseClass(
      @NotNull Class<T> commandClass,
      @NotNull Class<R> responseClass) throws CommandException {
    ResolvableType resolvableType = ResolvableType.forClassWithGenerics(CommandHandler.class, commandClass, responseClass);
    String[] beanNames = applicationContext.getBeanNamesForType(resolvableType);
    if (beanNames.length > 1) {
      throw new CommandException(String.format("Multiple command handlers found for command: %s", commandClass));
    }
    return Optional.ofNullable((CommandHandler<T, R>) applicationContext.getBeanProvider(resolvableType).getIfAvailable());
  }
}
