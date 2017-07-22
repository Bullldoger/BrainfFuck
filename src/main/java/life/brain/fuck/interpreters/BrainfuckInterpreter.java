package life.brain.fuck.interpreters;

import life.brain.fuck.exceptions.BrainFuckUncorrectSourceException;
import life.brain.fuck.interpreters.Context.SimpeContext;
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

    private SimpeContext context = new SimpeContext();
    private String currentSource;
    private List<String> sourceLines = new ArrayList<String>();

    public BrainfuckInterpreter() {

    }

    public void setProgramm(String programm) {

        this.sourceLines.add(programm);

        this.currentSource = sourceLines.get(0);
    }

    public void setProgramm(ArrayList<String> programm) {
        programm.clear();

        programm.stream().forEach(sourceLine -> {
            this.sourceLines.add(sourceLine);
        });

        this.currentSource = sourceLines.get(0);
    }

    public void checkSourceCodeCases() throws BrainFuckUncorrectSourceException {
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

                                if (!prevCommand.equals('[')) cases.pop();
                            } catch (EmptyStackException e) {
                                throw new BrainFuckUncorrectSourceException();
                            }
                        }
                        default: {
                            continue;
                        }
                    }
                }
            }

            if (cases.size() != 0) throw new BrainFuckUncorrectSourceException();
        } catch (BrainFuckUncorrectSourceException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
