package life.brain.fuck.interpreters.commands;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.interfaces.Command;
import life.brain.fuck.interpreters.context.interfaces.Context;

/**
 * Created by roman on 23.07.17.
 *
 * Складывает значения текущей и предыдущей и записывает итог в текущую
 */
public class SumComand implements Command {

    private String command = "S";
    private Context context;

    public SumComand(Context context) { this.context = context; }

    @Override
    public void runCommand() throws BrainFuckException {

        Integer index = this.context.getCurrentIndex();

        if (index < 1) {
            throw new BrainFuckException();
        } else {
            this.context.indexToLeft();
            Integer value = this.context.getCurrentValue();
            this.context.indexToRight();

            for(; value != 0; value--)
                this.context.incrementCurrentValue();
        }
    }

    @Override
    public String getCommand() {
        return this.command;
    }
}
