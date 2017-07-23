package life.brain.fuck.interpreters.commands;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.interfaces.Command;
import life.brain.fuck.interpreters.context.Context;

/**
 * Created by roman on 23.07.17.
 *
 * Складывает значения текущей и предыдущей и записывает итог в текущую
 */
public class SumComand implements Command {

    private Character command = 'S';
    private Context context;

    public SumComand(Context context) { this.context = context; }

    @Override
    public void interpret() throws BrainFuckException {

        Integer index = this.context.getCurrentIndex();

        if (index < 1) {
            throw new BrainFuckException();
        } else {
            this.context.positionToLeft();
            Integer value = this.context.getCurrentValue().intValue();
            this.context.positionToRight();

            for(; value != 0; value--)
                this.context.incCurrentValue();
        }
    }

    @Override
    public Character getCommand() {
        return this.command;
    }
}
