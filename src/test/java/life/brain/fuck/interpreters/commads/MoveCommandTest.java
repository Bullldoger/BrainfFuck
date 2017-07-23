package life.brain.fuck.interpreters.commads;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.commands.MoveCommand;
import life.brain.fuck.interpreters.context.Context;
import life.brain.fuck.interpreters.context.SimpleContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by roman on 23.07.17.
 */
public class MoveCommandTest {

    Context context = new SimpleContext();
    MoveCommand moveCommand;


    /**
     * Correct usage
     */
    @Test
    public void MoveCommandFirstTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.addCommand(new MoveCommand(context));
        context.setCurrentSource("++@--@");
        context.init();
        context.processProgram();
        Assert.assertTrue(context.getCurrentIndex() == 0);
    }

    /**
     * Uncorrect usage invoke BrainFuckException
     * @throws BrainFuckException
     */
    @Test(expected = BrainFuckException.class)
    public void MoveCommandSecondTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.addCommand(new MoveCommand(context));
        context.setCurrentSource("++@---@");
        context.init();
        context.processProgram();
        Assert.assertTrue(context.getCurrentIndex() == 0);
    }
}
