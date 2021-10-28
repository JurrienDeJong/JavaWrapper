package nl.bioinf.wrapper;

import org.apache.commons.cli.*;

public class CmdLineManager {
    public static void main(String[] args) throws ParseException {
        // define options
        Options options = new Options();

        Option alpha = new Option("a", "alpha", false, "Activate feature alpha");
        options.addOption(alpha);

        // define parser
        CommandLine cmd;
        CommandLineParser parser = new BasicParser();
        HelpFormatter helper = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
            if(cmd.hasOption("a")) {
                System.out.println("Alpha activated");
            }

            if (cmd.hasOption("c")) {
                String opt_config = cmd.getOptionValue("config");
                System.out.println("Config set to " + opt_config);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            helper.printHelp("Usage:", options);
            System.exit(0);
        }
    }
}

