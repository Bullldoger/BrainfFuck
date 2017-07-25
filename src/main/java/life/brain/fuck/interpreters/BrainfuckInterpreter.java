package life.brain.fuck.interpreters;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.MoveCommand;
import life.brain.fuck.interpreters.commands.SumComand;
import life.brain.fuck.interpreters.commands.ZeroCommand;
import life.brain.fuck.interpreters.context.interfaces.Context;
import life.brain.fuck.interpreters.context.SimpleContext;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Created by roman on 20.07.17.
 *
 * Базовый интерпретатор, возможна базовая конфигурация через конструктор
 */
public class BrainfuckInterpreter {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private Context context;
    private List<String> sources = new ArrayList<String>();

    public BrainfuckInterpreter(Boolean autoBuild) {

        if (autoBuild) {
            this.context = new SimpleContext();

            this.context.addCommand(new MoveCommand(this.context));
            this.context.addCommand(new SumComand(this.context));
            this.context.addCommand(new ZeroCommand(this.context));

            this.context.init();

        } else {

        }

    }

    public BrainfuckInterpreter(String source) throws BrainFuckException {
        this.setProgramm(source);
    }

    public BrainfuckInterpreter(ArrayList<String> sources) throws BrainFuckException {
        this.setProgramm(sources);
    }


    public void setProgramm(String programm) throws BrainFuckException {
        this.sources.clear();
        this.sources.add(programm);

        this.checkSourceCodeCases();
    }

    public void setProgramm(ArrayList<String> programm) throws BrainFuckException {
        this.sources.clear();
        programm.stream().forEach(sourceLine -> {
            this.sources.add(sourceLine);
        });

        this.checkSourceCodeCases();
    }

    public void addSource(String sourceCode) throws BrainFuckException {
        this.sources.add(sourceCode);
        this.checkSourceCodeCases();
    }

    public void checkSourceCodeCases() throws BrainFuckException {
        Stack<Character> cases = new Stack<>();

        for (String sourceLine : this.sources) {
            for (byte i = 0; i < sourceLine.length(); ++i) {
                char command = sourceLine.charAt(i);

                switch (command) {
                    case '[': {
                        cases.push(command);
                        break;
                    }
                    case ']': {
                        try {
                            Character prevCommand = cases.pop();
                        } catch (EmptyStackException e) {
                            throw new BrainFuckException();
                        }
                    }
                    default: {
                        continue;
                    }
                }
            }
        }

        if (cases.size() != 0) throw new BrainFuckException();

    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void runProgramm() throws BrainFuckException {
        for (String code :
                this.sources) {
            this.context.setSource(code);
            this.context.startProcess();
        }
    }
}
