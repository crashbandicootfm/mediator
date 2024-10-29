package dev.crashbandicootfm.mediator;

import dev.crashbandicootfm.mediator.model.CommandHandler;
import dev.crashbandicootfm.mediator.model.CommandRunner;
import dev.crashbandicootfm.mediator.service.CommandHandlerService;
import dev.crashbandicootfm.mediator.service.CommandRunnerService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MediatrImpl implements Mediatr {

  @NotNull CommandRunnerService commandRunnerService;

  @NotNull CommandHandlerService commandHandlerService;

  @Override
  @SuppressWarnings("unchecked")
  public <T, R> @NotNull R dispatch(@NotNull T command, @NotNull Class<R> responseClass) {
    Class<T> commandClass = (Class<T>) command.getClass();
    CommandHandler<T, R> handler = commandHandlerService.getByCommandClassAndResponseClass(commandClass, responseClass);
    return handler.handle(command);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> void dispatch(@NotNull T command) {
    Class<T> commandClass = (Class<T>) command.getClass();
    CommandRunner<T> runner = commandRunnerService.getByCommandClass(commandClass);
    runner.run(command);
  }
}
