package dev.crashbandicootfm.mediator;

import org.jetbrains.annotations.NotNull;

public interface Mediatr {

  <T, R> @NotNull R dispatch(@NotNull T command, @NotNull Class<R> responseClass);

  <T> void dispatch(@NotNull T command);
}
