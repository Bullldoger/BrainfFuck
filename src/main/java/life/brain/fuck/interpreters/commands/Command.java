package life.brain.fuck.interpreters.commands;

import life.brain.fuck.exceptions.BrainFuckException;

/**
 * Created by roman on 21.07.17.
 *
 * Интерфейс команды, тип T -
 *
 */
public interface Command {

    public void interpret();
    public Character getCommand();

}
