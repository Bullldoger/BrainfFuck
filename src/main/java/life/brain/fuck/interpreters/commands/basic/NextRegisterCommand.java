package life.brain.fuck.interpreters.commands.basic;

import life.brain.fuck.interpreters.Context.Context;
import life.brain.fuck.interpreters.commands.Command;

/**
 * Created by roman on 21.07.17.
 */
public class NextRegisterCommand implements Command {

    private Character command = '>';
    private Context context;

    public NextRegisterCommand(Context context) {
        this.context = context;
    }

    @Override
    public void interpret() {
        context.positionToRight();
    }

    @Override
    public Character getCommand() {
        return this.command;
    }
}
