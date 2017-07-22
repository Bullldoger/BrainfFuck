package life.brain.fuck.interpreters.Context;

import java.util.List;

/**
 * Created by roman on 21.07.17.
 */
public interface Context {

    public void processProgram();

    public void setIndexPosition(Integer position);
    public Integer getCurrentIndex();
    public void positionToRight();
    public void positionToLeft();
    public void incCurrentValue();
    public void decCurrentValue();
    public void setCurrentSource(String source);
    public String getCurrentSource();
    public Integer getProgramCursor();
    public void setProgramCursor(Integer programCursor);
    public Byte getCurrentValue();
    public void step(Character command);
    public void addToOutput();

}
