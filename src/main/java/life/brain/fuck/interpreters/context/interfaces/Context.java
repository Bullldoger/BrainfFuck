package life.brain.fuck.interpreters.context.interfaces;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.interfaces.Command;

import java.util.List;

/**
 * Created by roman on 21.07.17.
 *
 * Обязательные метода контекста для реализации
 *
 */
public interface Context {

    //BrainFuck basic operations
    public void positionToRight() throws BrainFuckException;
    public void positionToLeft() throws BrainFuckException;
    public void incCurrentValue();
    public void decCurrentValue();
    public void addToOutput();

    //Programm start
    public void processProgram() throws BrainFuckException;

    //Context preference
    public void setCurrentSource(String source);
    public void setProgramCursor(Integer programCursor);
    public void addCommand(Command command);
    public void init();

    public List<String> getResult();

    //Current index, value
    public Integer getCurrentIndex();
    public Integer getCurrentValue();
}
