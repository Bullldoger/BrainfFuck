package life.brain.fuck.interpreters.commands;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.interfaces.Command;
import life.brain.fuck.interpreters.context.interfaces.Context;

/**
 * Created by roman on 23.07.17.
 *
 * Обнуляет текущую ячейку
 *
 */
public class ZeroCommand implements Command {

    private Context context;
    private String command = "Z";

    public ZeroCommand(Context context) { this.context = context; }


    @Override
    public void interpret() throws BrainFuckException {
        Integer currentValue = this.context.getCurrentValue().intValue();

        if (currentValue > 0)
            for (;currentValue != 0; currentValue--)
                this.context.decCurrentValue();

        else
            for (;currentValue != 0; currentValue++)
                this.context.incCurrentValue();
    }

    @Override
    public String getCommand() {
        return this.command;
    }
}
