package gitlet;

import java.util.Set;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        if (args.length == 0) {
            System.out.println("Please Enter a valid command.");
            System.exit(0);
        }
        String command = args[0];
        switch(command) {
            case "init":
                // TODO: handle the `init` command
                MyUtils.checkArgSum(args, 1);
                Repository.init();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                MyUtils.checkArgSum(args, 2);
                Repository.add(args[1]);
                break;
            // TODO: FILL THE REST IN
            case "commit":
                MyUtils.checkArgSum(args, 2);
                Repository.commit(args[1]);
                break;
        }
    }
}
