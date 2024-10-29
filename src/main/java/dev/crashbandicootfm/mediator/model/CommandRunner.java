package dev.crashbandicootfm.mediator.model;

public interface CommandRunner<T> {

  void run(T command);
}
