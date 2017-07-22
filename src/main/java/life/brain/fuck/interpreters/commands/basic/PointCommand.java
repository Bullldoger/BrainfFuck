package life.brain.fuck.interpreters.commands.basic;

import life.brain.fuck.interpreters.Context.Context;
import life.brain.fuck.interpreters.commands.Command;

/**
 * Created by roman on 22.07.17.
 */
public class PointCommand implements Command {

    private Character command = '.';
    private Context context;

    public PointCommand(Context context) {
        this.context = context;
    }

    @Override
    public void interpret() {

    }

    @Override
    public Character getCommand() {
        return null;
    }
}
