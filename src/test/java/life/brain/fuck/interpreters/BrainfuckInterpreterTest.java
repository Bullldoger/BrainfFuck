package life.brain.fuck.interpreters;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.context.SimpleContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by roman on 23.07.17.
 */
public class BrainfuckInterpreterTest {

    BrainfuckInterpreter brainfuckInterpreter = new BrainfuckInterpreter(false);
    SimpleContext context = new SimpleContext();

    /*
    * Uncorrect source code
    * */
    @Test(expected = BrainFuckException.class)
    public void BrainfuckInterpreterFirstTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        brainfuckInterpreter.setContext(context);
        brainfuckInterpreter.setProgramm("++[");
        brainfuckInterpreter.runProgramm();

        Assert.assertTrue(brainfuckInterpreter.getContext().getCurrentValue() == 2);

    }

    /*
    * Correct source code
    * */
    @Test
    public void BrainfuckInterpreterSecondTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        brainfuckInterpreter.setContext(context);
        brainfuckInterpreter.setProgramm("++[>++<-]>");
        brainfuckInterpreter.runProgramm();

        Assert.assertTrue(brainfuckInterpreter.getContext().getCurrentValue() == 4);

    }
}
