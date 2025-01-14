package commands;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bot.Bot;
import tasks.Task;

/**
 * Command for listing Bot's taskList
 */
public class ListCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) {
        List<Task> taskList = bot.getTaskList().get();
        return commandsToStrings(taskList);
    }

    /**
     * Converts list of tasks to list of string
     * representation of tasks
     *
     * @param tasks List of tasks
     * @return List of string representation of tasks
     */
    public static String[] commandsToStrings(List<Task> tasks) {
        List<String> taskStrings = IntStream.range(0, tasks.size())
            .mapToObj(i -> {
                Task t = tasks.get(i);
                return String.format("%d. %s", i + 1, t.toString());
            })
            .collect(Collectors.toList());
        return taskStrings.toArray(new String[0]);
    }

}
