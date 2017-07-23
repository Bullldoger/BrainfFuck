package life.brain.fuck.interpreters.context;

import life.brain.fuck.interpreters.context.Context;
import life.brain.fuck.interpreters.context.SimpleContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by roman on 22.07.17.
 */
public class SimpleContextTest {

    Context context = new SimpleContext();

    @Test
    public void SimpleContextTestFirstTest() {
        org.apache.log4j.BasicConfigurator.configure();

        context.setCurrentSource("++[>++<-]>(2^2)");
        context.processProgram();
        int x = context.getCurrentIndex();
        Assert.assertTrue(context.getCurrentValue() == 4);
    }

    @Test
    public void SimpleContextTestSecondTest() {
        org.apache.log4j.BasicConfigurator.configure();

        context.setCurrentSource("++[>++[>++[>++<-]<-]<-]>>>(2^4)");
        context.processProgram();

        Assert.assertTrue(context.getCurrentValue() == 16);
    }

    @Test
    public void SimpleContextTestThirdTest() {
        org.apache.log4j.BasicConfigurator.configure();

        context.setCurrentSource("+++++(5)>+++++++(7)[>+<-]<[>>+<<-]>>");
        context.processProgram();

        Assert.assertTrue(context.getCurrentValue() == 12);
    }

}
