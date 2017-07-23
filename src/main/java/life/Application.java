package life;

/**
 * Created by roman on 19.07.17.
 */

import life.brain.fuck.exceptions.BrainFuckException;
import life.brain.fuck.interpreters.BrainfuckInterpreter;
import life.brain.fuck.interpreters.commands.MoveCommand;
import life.brain.fuck.interpreters.commands.SumComand;
import life.brain.fuck.interpreters.commands.ZeroCommand;
import life.brain.fuck.interpreters.context.Context;
import life.brain.fuck.interpreters.context.SimpleContext;
import org.apache.log4j.Logger;

public class Application    {

    Logger logger = Logger.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure();

        try {
            BrainfuckInterpreter brainfuckInterpreter = new BrainfuckInterpreter(true);
            brainfuckInterpreter.setProgramm("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
            brainfuckInterpreter.runProgramm();

            for (String s:
                 brainfuckInterpreter.getContext().getResult()) {
                System.out.println(s);

            }
        } catch (BrainFuckException e) {
            System.out.println(e.getMessage());
        }
    }
}
