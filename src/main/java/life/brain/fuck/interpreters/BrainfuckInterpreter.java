package life.brain.fuck.interpreters;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.context.Context;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Created by roman on 20.07.17.
 */
public class BrainfuckInterpreter {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private Context context;
    private String currentSource;
    private List<String> sourceLines = new ArrayList<String>();

    public BrainfuckInterpreter() {

    }

    public BrainfuckInterpreter(String _programm) throws BrainFuckException {
        this.setProgramm(_programm);
    }

    public BrainfuckInterpreter(ArrayList<String> _programm) throws BrainFuckException {
        this.setProgramm(_programm);
    }


    public void setProgramm(String programm) throws BrainFuckException {
        this.sourceLines.add(programm);

        this.checkSourceCodeCases();
    }

    public void setProgramm(ArrayList<String> programm) throws BrainFuckException {
        programm.clear();

        programm.stream().forEach(sourceLine -> {
            this.sourceLines.add(sourceLine);
        });

        this.checkSourceCodeCases();
    }

    public void checkSourceCodeCases() throws BrainFuckException {
        Stack<Character> cases = new Stack<>();
        try {
            for (String sourceLine : this.sourceLines) {
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
        } catch (BrainFuckException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void setContext(Context context) { this.context = context; }
    public Context getContext() { return this.context; }

    public void runProgramm() {
        for (String code:
             this.sourceLines) {
            this.context.setCurrentSource(code);
            this.context.processProgram();
        }
    }
}
