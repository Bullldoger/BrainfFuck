package life.brain.fuck.interpreters.context;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.interfaces.Command;
import life.brain.fuck.interpreters.context.interfaces.Context;
import life.brain.fuck.utils.Pair;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by roman on 21.07.17.
 *
 * Базовые контекст, реализующий основные возможности
 *
 */
public class SimpleContext implements Context {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private Integer[] context;
    private Integer contextSize = 30000;
    private Integer i;
    private Integer programCursor;

    private List<Integer> breakPointsPositions = new ArrayList<>();
    private List<String> output = new ArrayList<>();

    private Map<String, Command> methods = new HashMap<>();
    private Set<String> basicCommands = new HashSet<>();
    private List<Command> commands = new ArrayList<Command>();

    private String currentSource;

    public SimpleContext(Integer contextSize) {
        this.contextSize = contextSize;

        init();

        for (int k = 0; k < this.contextSize; k++)
            this.context[k] = 0;
    }

    public SimpleContext() {
        context = new Integer[this.contextSize];

        init();

        for (int k = 0; k < contextSize; k++)
            this.context[k] = 0;
    }

    public void init() {
        this.refresh();
        this.output.clear();
        this.programCursor = 0;
        this.i = 0;

        for (int k = 0; k < contextSize; k++)
            this.context[k] = 0;
    }

    @Override
    public void processProgram() throws BrainFuckException {
        this.subSource(this.currentSource);
        logger.info("processing finished");
    }

    @Override
    public void addToOutput() {
        this.output.add(String.format("array[%d] = %d", this.i, this.getCurrentValue()));
    }

    @Override
    public List<String> getResult() {
        return this.output;
    }

    @Override
    public void addCommand(Command command) {
        this.commands.add(command);
    }

    @Override
    public Integer getCurrentIndex() {
        return this.i;
    }

    @Override
    public void positionToRight() throws BrainFuckException {
        if (this.i == this.contextSize - 1) throw new BrainFuckException();
        else this.i += 1;
    }

    @Override
    public void positionToLeft() throws BrainFuckException {

        if (this.i == 0) throw new BrainFuckException();
        else this.i -= 1;
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
    public void setProgramCursor(Integer programCursor) {
        this.programCursor = programCursor;
    }

    @Override
    public Integer getCurrentValue() {
        return this.context[this.getCurrentIndex()];
    }

    private void refresh() {
        basicCommands.add("+");
        basicCommands.add("-");
        basicCommands.add(">");
        basicCommands.add("<");
        basicCommands.add("[");
        basicCommands.add("]");
        basicCommands.add(".");

        for (Command c:
             commands)
            this.methods.put(String.valueOf(c.getCommand()), c);

    }

    private Pair<Integer, Integer> getSubBracketsCode(Integer startPositionm, String subSource) {
        Stack<Character> cases = new Stack<>();

        cases.push('[');
        Integer startPosition = programCursor;
        Integer finishPosition;

        for (finishPosition = programCursor + 1; !cases.isEmpty(); finishPosition++) {

            String cmd = String.valueOf(subSource.charAt(finishPosition));

            switch (cmd) {

                case "[": {
                    cases.push('[');
                    break;
                }
                case "]": {
                    cases.pop();
                    break;
                }
                default:
                    continue;
            }

            if (cases.size() == 0)
                break;
        }

        return new Pair<Integer, Integer>(startPosition, finishPosition);
    }

    private void subSource(String subSource) throws BrainFuckException {

        for (this.programCursor = 0; programCursor < subSource.length(); ++programCursor) {
            String command = String.valueOf(subSource.charAt(programCursor));

            if (this.breakPointsPositions.contains(this.programCursor))
                logger.info(String.format("array[%d] = %d", this.i, this.context[this.i]));

            if (basicCommands.contains(command)) {
                switch (command) {
                    case ".": {
                        this.addToOutput();
                        break;
                    }
                    case "+": {
                        this.incCurrentValue();
                        break;
                    }
                    case "-": {
                        this.decCurrentValue();
                        break;
                    }
                    case ">": {
                        this.positionToRight();
                        break;
                    }
                    case "<": {
                        this.positionToLeft();
                        break;
                    }
                    case "[": {

                        Pair<Integer, Integer> p = this.getSubBracketsCode(this.programCursor, subSource);

                        Integer startPosition = p.getFirst();
                        Integer finishPosition = p.getSecond();

                        while (this.context[this.i] > 0) {
                            String tempProc = subSource.substring(startPosition + 1, finishPosition);
                            this.subSource(tempProc);
                        }

                        this.setProgramCursor(finishPosition);
                    }
                    default:
                        continue;
                }
            } else {
                List<String> tempCommands = new ArrayList<>();

                for (String key:
                        this.methods.keySet())
                    if (key.indexOf(command) == 0) tempCommands.add(key);

                for (String key:
                        tempCommands) {

                    String cmd = subSource.substring(this.programCursor, this.programCursor + key.length());

                    if (key.equals(cmd))
                        this.methods.get(key).interpret();

                    this.programCursor += key.length() - 1;
                }
            }
        }
    }
}
