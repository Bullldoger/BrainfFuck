package life.brain.fuck.interpreters.commads;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.MoveCommand;
import life.brain.fuck.interpreters.commands.ZeroCommand;
import life.brain.fuck.interpreters.context.Context;
import life.brain.fuck.interpreters.context.SimpleContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by roman on 23.07.17.
 */
public class ZeroCommandTest {

    Context context = new SimpleContext();
    MoveCommand moveCommand;

    /**
     * Correct usage
     */
    @Test
    public void ZeroCommandFirstTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.addCommand(new ZeroCommand(context));
        context.setCurrentSource("++++++Z");
        context.init();
        context.processProgram();
        Assert.assertTrue(context.getCurrentValue() == 0);
    }

    /**
     * Correct usage
     */
    @Test
    public void ZeroCommandSecondTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.addCommand(new ZeroCommand(context));
        context.setCurrentSource("-----Z");
        context.init();
        context.processProgram();
        Assert.assertTrue(context.getCurrentValue() == 0);
    }
}
