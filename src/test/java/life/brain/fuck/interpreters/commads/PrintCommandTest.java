package life.brain.fuck.interpreters.commads;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.PrintCommand;
import life.brain.fuck.interpreters.commands.SumComand;
import life.brain.fuck.interpreters.context.SimpleContext;
import life.brain.fuck.interpreters.context.interfaces.Context;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by roman on 24.07.17.
 */
public class PrintCommandTest {

    Context context = new SimpleContext();


    /**
     * Correct usage (2 + 2)
     */
    @Test
    public void PrintCommandFirstTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.addCommand(new PrintCommand(context));
        context.addCommand(new SumComand(context));
        context.setCurrentSource("++>++S PRINT PRINT PRINT");
        context.init();
        context.processProgram();
        Assert.assertTrue(context.getCurrentValue() == 4);
    }

}
