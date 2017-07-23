package life.brain.fuck.interpreters.context;

import life.brain.fuck.interpreters.commands.Command;
import life.brain.fuck.utils.Pair;
import org.apache.log4j.Logger;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * Created by roman on 21.07.17.
 */
public class SimpleContext implements Context {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private OutputStreamWriter outputStreamWriter;
    private InputStreamReader inputStreamReader;

    private Byte[] context;

    private Integer i;
    private Integer programCursor;
    private List<Integer> breakPointsPositions = new ArrayList<>();

    private Map<Character, Command> methods;
    private Set<Character> basicCommands = new HashSet<>();

    private List<Command> commands = new ArrayList<Command>();
    private String currentSource;

    public SimpleContext(Integer contextSize) {
        context = new Byte[contextSize];

        for (int k = 0; k < contextSize; ++k)
            this.context[k] = 0;
    }

    public SimpleContext() {
        context = new Byte[30000];

        for (int k = 0; k < 30000; ++k)
            this.context[k] = 0;
    }

    @Override
    public void processProgram() {

        this.refresh();

        this.programCursor = 0;
        this.i = 0;

        this.subSource(this.currentSource);

        logger.info("processing finished");
    }


    private void subSource(String subSource) {

        for (this.programCursor = 0; programCursor < subSource.length(); ++programCursor) {
            Character command = subSource.charAt(programCursor);

            if (this.breakPointsPositions.contains(this.programCursor))
                logger.info(String.format("array[%d] = %d", this.i, this.context[this.i]));

            switch (command) {
                case '+': {
                    this.incCurrentValue();
                    break;
                }
                case '-': {
                    this.decCurrentValue();
                    break;
                }
                case '>': {
                    this.positionToRight();
                    break;
                }
                case '<': {
                    this.positionToLeft();
                    break;
                }
                case '[': {

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
        }
    }


    @Override
    public void addToOutput() {
        logger.info(String.format("array[%d] = %d", this.i, this.getCurrentValue()));
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
    public String getCurrentSource() {
        return this.currentSource;
    }

    @Override
    public void setCurrentSource(String source) {
        this.currentSource = source;
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
        return this.context[this.getCurrentIndex()];
    }

    private void refresh() {
        basicCommands.add('+');
        basicCommands.add('-');
        basicCommands.add('>');
        basicCommands.add('<');
        basicCommands.add('[');
        basicCommands.add(']');
    }

    private Pair<Integer, Integer> getSubBracketsCode(Integer startPositionm, String subSource) {
        Stack<Character> cases = new Stack<>();

        cases.push('[');
        Integer startPosition = programCursor;
        Integer finishPosition;

        for (finishPosition = programCursor + 1; !cases.isEmpty(); finishPosition++) {

            Character cmd = subSource.charAt(finishPosition);

            switch (cmd) {

                case '[': {
                    cases.push('[');
                    break;
                }
                case ']': {
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

}
