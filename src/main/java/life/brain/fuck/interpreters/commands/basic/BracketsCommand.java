package life.brain.fuck.interpreters.commands.basic;

import life.brain.fuck.exceptions.BrainFuckUncorrectSourceException;
import life.brain.fuck.interpreters.Context.Context;
import life.brain.fuck.interpreters.commands.Command;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by roman on 22.07.17.
 */
public class BracketsCommand implements Command {

    private Character command = '[';

    private Character openCommand = '[';
    private Character closeCommand = ']';

    private Context context;

    public BracketsCommand(Context context) {
        this.context = context;
    }

    @Override
    public Character getCommand() {
        return null;
    }

    @Override
    public void interpret() {

    }

    private void runInterpr() {
        Integer startPosition = this.context.getProgramCursor();
        Integer finishPosition = startPosition;
        Byte currentValue = this.context.getCurrentValue();

        String source = this.context.getCurrentSource();

        Stack<Character> cases = new Stack<>();
        cases.push('[');

        for (finishPosition = startPosition + 1; cases.size() != 0; finishPosition++) {

            Character command = source.charAt(finishPosition);

            switch (command) {

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
        }

        while (this.context.getCurrentValue() > 0) {

            int i = startPosition + 1;

            for (;i < finishPosition - 1; i++) {
                Character command = this.context.getCurrentSource().charAt(i);
                this.context.step(command);
            }
        }
    }
}
