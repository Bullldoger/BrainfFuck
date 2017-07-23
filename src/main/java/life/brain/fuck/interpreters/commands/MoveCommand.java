package life.brain.fuck.interpreters.commands;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.context.Context;

/**
 * Created by roman on 23.07.17.
 *
 * Сдвигает каретку на currentValue ячеек
 *
 */
public class MoveCommand implements Command {

    private Context context;
    private Character command = 'M';

    public MoveCommand(Context context) { this.context = context; }

    @Override
    public void interpret() throws BrainFuckException {
        Integer steps = this.context.getCurrentValue().intValue();

        if (steps < 0) {
            steps = Math.abs(steps);

            if (steps > this.context.getCurrentIndex())
                throw new BrainFuckException("Array out of Bounds");

            for (; steps > 0; steps--)
                this.context.positionToLeft();
        } else {
            for (; steps > 0; steps--)
                this.context.positionToRight();
        }
    }

    @Override
    public Character getCommand() {
        return this.command;
    }
}
