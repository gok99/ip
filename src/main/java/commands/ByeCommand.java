package commands;

import bot.Bot;

public class ByeCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    bot.stop();
  }
  
}
