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
    public void indexToRight() throws BrainFuckException;
    public void indexToLeft() throws BrainFuckException;
    public void incrementCurrentValue();
    public void decrementCurrentValue();
    public void print();

    //Programm start
    public void startProcess() throws BrainFuckException;

    //Context preference
    public void setSource(String source);
    public void setSourceCursor(Integer programCursor);
    public void addCommand(Command command);
    public void init();

    public List<String> getResult();

    //Current index, value
    public Integer getCurrentIndex();
    public Integer getCurrentValue();
}
