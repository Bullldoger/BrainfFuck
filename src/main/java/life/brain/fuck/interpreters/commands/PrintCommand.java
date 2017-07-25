package life.brain.fuck.interpreters.commands;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.interfaces.Command;
import life.brain.fuck.interpreters.context.interfaces.Context;
import org.apache.log4j.Logger;

/**
 * Created by roman on 24.07.17.
 */
public class PrintCommand implements Command {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String command = "PRINT";
    private Context context;

    public PrintCommand(Context context)   {
        this.context = context;
    }


    @Override
    public void runCommand() throws BrainFuckException {
        this.logger.info(this.context.getCurrentValue());
    }

    @Override
    public String getCommand() {
        return this.command;
    }
}
