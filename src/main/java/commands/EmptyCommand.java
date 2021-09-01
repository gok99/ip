package commands;

import bot.Bot;
import bot.Ui;

/**
 * Empty command for unrecognised input
 */
public class EmptyCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) {
        return new String[] {
            Ui.ERROR_SIGNATURE + "I'm sorry, but I don't know what that means :-("
        };
    }

}
