package life.brain.fuck.interpreters.Context;

import life.brain.fuck.utils.Pair;
import life.brain.fuck.interpreters.commands.Command;
import org.apache.log4j.Logger;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 21.07.17.
 */
public class SimpeContext implements Context {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private OutputStreamWriter outputStreamWriter;
    private InputStreamReader inputStreamReader;

    private Byte[] context;

    private Integer i;
    private Integer programCursor;
    private List<Byte> breakPointsPositions;
    private Map<Character, Command> methods;
    private Byte currentValue;

    private List<Command> commands = new ArrayList<Command>();
    private String currentSource;

    public SimpeContext(Integer contextSize) {
        context = new Byte[contextSize];
    }

    public SimpeContext() {
        context = new Byte[30000];
    }

    @Override
    public void processProgram() {
        this.refresh();

    }

    public void step(Character command) {
        if (this.methods.containsKey(command)) {
            Command cmd = this.methods.get(command);
            cmd.interpret();
        }
    }

    @Override
    public void addToOutput() {
        logger.info(String.format("array[%d] = %d", this.i, this.currentValue));
    }

    @Override
    public void setIndexPosition(Integer position) {
        this.i = position;
    }

    @Override
    public Integer getCurrentIndex() {
        return this.i;
    }

    @Override
    public void positionToRight() {
        this.i += 1;
    }

    @Override
    public void positionToLeft() {
        this.i -= 1;
    }

    public void incCurrentValue() {
        this.context[this.i]++;
    }

    public void decCurrentValue() {
        this.context[this.i]--;
    }

    @Override
    public void setCurrentSource(String source) {
        this.currentSource = source;
    }

    @Override
    public String getCurrentSource() {
        return this.currentSource;
    }

    @Override
    public Integer getProgramCursor() {
        return this.programCursor;
    }

    @Override
    public void setProgramCursor(Integer programCursor) {
        this.programCursor = programCursor;
    }

    @Override
    public Byte getCurrentValue() {
        return this.currentValue;
    }

    private void refresh() {
        this.methods = new HashMap<>();

        for (Command c:
             this.commands) {
            Character command = c.getCommand();

            this.methods.put(command, c);
        }
    }
}
