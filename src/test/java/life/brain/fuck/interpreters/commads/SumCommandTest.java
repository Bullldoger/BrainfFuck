package life.brain.fuck.interpreters.commads;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.MoveCommand;
import life.brain.fuck.interpreters.commands.SumComand;
import life.brain.fuck.interpreters.context.interfaces.Context;
import life.brain.fuck.interpreters.context.SimpleContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by roman on 23.07.17.
 */
public class SumCommandTest {

    Context context = new SimpleContext();
    MoveCommand moveCommand;


    /**
     * Correct usage (2 + 2)
     */
    @Test
    public void SumCommandFirstTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.addCommand(new SumComand(context));
        context.setSource("++>++S");
        context.init();
        context.startProcess();
        Assert.assertTrue(context.getCurrentValue() == 4);
    }

    /**
     * Correct usage (4 + 0)
     */
    @Test
    public void SumCommandSecondTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.addCommand(new SumComand(context));
        context.setSource("++++>--S");
        context.init();
        context.startProcess();
        Assert.assertTrue(context.getCurrentValue() == 2);
    }

    /**
     * Uncorrect usage
     * @throws BrainFuckException
     */
    @Test(expected = BrainFuckException.class)
    public void SumCommandThirdTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.addCommand(new SumComand(context));
        context.setSource("++++S");
        context.init();
        context.startProcess();
        Assert.assertTrue(context.getCurrentValue() == 2);
    }
}
