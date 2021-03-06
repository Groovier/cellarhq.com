package com.cellarhq.cli

import com.cellarhq.commands.*

/**
 * Offers an entry point into CLI jobs.
 */
class CommandApp {

  final static void main(String[] args) {
    if (args.size() == 0) {
      println('usage: chq [command]')
      System.exit(1)
    }

    NamedCommand command = [
      new CellarMergeCommand(),
      new UpdateCountsCommand(),
      new RepairDuplicatesCommand(),
      new DbImportCommand()
    ].find { it.name == args[0] }
    if (!command) {
      throw new IllegalArgumentException("Cannot find command with name '${args[0]}'")
    }

    String[] commandArgs = []
    if (args.size() > 1) {
      commandArgs = args[1..args.size() - 1]
    }
    command.configure((String[]) commandArgs)
    System.exit(command.run() ? 0 : 1)
  }
}
