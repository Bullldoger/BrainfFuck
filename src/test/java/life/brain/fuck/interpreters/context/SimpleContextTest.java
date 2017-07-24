package life.brain.fuck.interpreters.context;

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.context.interfaces.Context;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by roman on 22.07.17.
 */
public class SimpleContextTest {

    Context context = new SimpleContext();

    @Test
    public void SimpleContextFirstTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.setCurrentSource("++[>++<-]>(2^2)");
        context.processProgram();

        int x = context.getCurrentIndex();
        Assert.assertTrue(context.getCurrentValue() == 4);
    }

    @Test
    public void SimpleContextSecondTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.setCurrentSource("++[>++[>++[>++<-]<-]<-]>>>(2^4)");
        context.processProgram();

        Assert.assertTrue(context.getCurrentValue() == 16);
    }

    @Test
    public void SimpleContextThirdTest() throws BrainFuckException {
        org.apache.log4j.BasicConfigurator.configure();

        context.setCurrentSource("+++++(5)>+++++++(7)[>+<-]<[>>+<<-]>>");
        context.processProgram();

        Assert.assertTrue(context.getCurrentValue() == 12);
    }

}
