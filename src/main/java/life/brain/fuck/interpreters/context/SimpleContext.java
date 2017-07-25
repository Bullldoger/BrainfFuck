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
    private Integer CONTEXT_SIZE = 30000;
    private Integer index;
    private Integer programCursor;

    private List<Integer> breakPointsPositions = new ArrayList<>();
    private List<String> results = new ArrayList<>();

    private List<Pair<String, Command>> profCommands = new ArrayList<>();
    private Set<String> mainCommands = new HashSet<>();
    private List<Command> commands = new ArrayList<>();

    private String currentSource;

    public SimpleContext(Integer contextSize) {
        this.CONTEXT_SIZE = contextSize;

        init();

        for (int k = 0; k < this.CONTEXT_SIZE; k++)
            this.context[k] = 0;
    }

    public SimpleContext() {
        context = new Integer[this.CONTEXT_SIZE];

        init();

        for (int k = 0; k < CONTEXT_SIZE; k++)
            this.context[k] = 0;
    }

    public void init() {
        this.refresh();
        this.results.clear();
        this.programCursor = 0;
        this.index = 0;

        for (int k = 0; k < CONTEXT_SIZE; k++)
            this.context[k] = 0;
    }

    @Override
    public void startProcess() throws BrainFuckException {
        this.subSource(this.currentSource);
        logger.info("processing finished");
    }

    @Override
    public void print() {
        this.results.add(String.format("array[%d] = %d", this.index, this.getCurrentValue()));
    }

    @Override
    public List<String> getResult() {
        return this.results;
    }

    @Override
    public void addCommand(Command command) {
        this.commands.add(command);
    }

    @Override
    public Integer getCurrentIndex() {
        return this.index;
    }

    @Override
    public void indexToRight() throws BrainFuckException {
        if (this.index == this.CONTEXT_SIZE - 1) throw new BrainFuckException();
        else this.index += 1;
    }

    @Override
    public void indexToLeft() throws BrainFuckException {

        if (this.index == 0) throw new BrainFuckException();
        else this.index -= 1;
    }

    public void incrementCurrentValue() {
        this.context[this.index]++;
    }

    public void decrementCurrentValue() {
        this.context[this.index]--;
    }

    @Override
    public void setSource(String source) {
        this.currentSource = source;
    }

    @Override
    public void setSourceCursor(Integer programCursor) {
        this.programCursor = programCursor;
    }

    @Override
    public Integer getCurrentValue() {
        return this.context[this.getCurrentIndex()];
    }

    private void refresh() {
        mainCommands.add("+");
        mainCommands.add("-");
        mainCommands.add(">");
        mainCommands.add("<");
        mainCommands.add("[");
        mainCommands.add("]");
        mainCommands.add(".");

        for (Command c:
             this.commands)
            this.profCommands.add(new Pair<String, Command>(c.getCommand(), c));

        this.profCommands.sort(new Comparator<Pair<String, Command>>() {
            @Override
            public int compare(Pair<String, Command> t1, Pair<String, Command> t2) {
                //Negative, if t1 < t2
                //Positive, if t1 > t2
                //Zero, if t1 == t2

                if (t1 != null && t2 != null) {
                    boolean f = firstLessThenSecond(t1, t2);

                    if (f) return -1;
                    else return 1;

                } else return -1;
            }

            private boolean firstLessThenSecond(Pair<String, Command> t1, Pair<String, Command> t2) {

                if (t1.getFirst().length() < t2.getFirst().length()) return false;
                else if (t1.getFirst().length() > t2.getFirst().length()) return true;
                else {
                    boolean f = true;
                    for (int i = 0; f && i < t1.getFirst().length(); ++i)
                        f = (t1.getFirst().charAt(i) == t2.getFirst().charAt(i));

                    return f;
                }
            }
        });
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
                logger.info(String.format("array[%d] = %d", this.index, this.context[this.index]));

            if (mainCommands.contains(command)) this.interpBasicCommand(subSource);
            else this.interpProfCommand(subSource);
        }
    }

    private void interpBasicCommand(String subSource) throws BrainFuckException {
        String command = String.valueOf(subSource.charAt(this.programCursor));
        switch (command) {
            case ".": {
                this.print();
                break;
            }
            case "+": {
                this.incrementCurrentValue();
                break;
            }
            case "-": {
                this.decrementCurrentValue();
                break;
            }
            case ">": {
                this.indexToRight();
                break;
            }
            case "<": {
                this.indexToLeft();
                break;
            }
            case "[": {

                Pair<Integer, Integer> p = this.getSubBracketsCode(this.programCursor, subSource);

                Integer startPosition = p.getFirst();
                Integer finishPosition = p.getSecond();

                while (this.context[this.index] > 0) {
                    String tempProc = subSource.substring(startPosition + 1, finishPosition);
                    this.subSource(tempProc);
                }

                this.setSourceCursor(finishPosition);
            }
            default:
                return;
        }
    }

    private void interpProfCommand(String subSource) throws BrainFuckException {
        List<String> tempCommands = new ArrayList<>();

        for (Pair<String, Command> pair:
                this.profCommands) {

            String COMMAND = pair.getFirst();
            Command commandObject = pair.getSecond();

            String cmd = subSource.substring(this.programCursor, this.programCursor + COMMAND.length());
            if (cmd.equals(COMMAND)) {
                commandObject.runCommand();
                this.programCursor += COMMAND.length() - 1;
            }
        }
    }
}
