package life.brain.fuck.interpreters.commands;

import life.brain.fuck.exceptions.BrainFuckException;

/**
 * Created by roman on 21.07.17.
 *
 * Интерфейс команды, 2 основных метода для реализации
 *
 */
public interface Command {

    public void interpret() throws BrainFuckException;
    public Character getCommand();

}
