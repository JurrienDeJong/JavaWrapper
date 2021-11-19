package nl.bioinf.wrapper;

import org.apache.commons.cli.*;

/**
 * This class regulates all things user-input related.
 * It will implement all methods from interface ProvideOptions
 * When arguments are given, they are checked first.
 * Afterward. Send the values over to the main for the next steps.
 * @author Jurrien de Jong
 * @version 1.0
 */


public class CmdLineManager implements ProvideOptions{
    protected Options options;
    protected CommandLine cmd;
    protected String file_path;
    protected double serSodium;
    protected double serCreatinin;
    protected int age;
    protected boolean useFile = false;

    /**
     * Functions as an init
     * @param args given user args
     */
    public CmdLineManager(String[] args) {
        addOptions();
        argParse(args);
    }

    /**
     * Adds options which a user can give the program
     */
    private void addOptions(){
        this.options = new Options();
        options.addOption(new Option("h",
                "number",
                false,
                "Print the Java Wrapper help."));
        options.addOption(new Option("a",
                "age ( in years )",
                true,
                "The age of the patient - in years."));
        options.addOption(new Option("c",
                "Serum Creatinine ( in mg/dL )",
                true,
                "Level of serum creatinine in the blood - in mg/dL"));
        options.addOption(new Option("s",
                "Serum Sodium ( in mEq/L )",
                true,
                "Level of serum creatinine in the blood - in mEq/L"));
        options.addOption(new Option("f",
                "file",
                true,
                "The file with instances to be classified."));
    }

    /**
     * Adds options which a user can give the program
     * @param args Given user args
     */
    private void argParse(String[] args) {
        // Parse the arguments using a CommandLineParser
        CommandLineParser parser = new BasicParser();
        try {
            this.cmd = parser.parse(options, args);
            if (cmd.hasOption('h')) {
                returnHelp();
            }
            // Check if args are valid:
            checkArguments();
        } catch (ParseException e) {
            System.out.println("Something went wrong while parsing, cause: "
                    + e.getMessage());
            returnHelp();
        }
    }

    /**
     * Check if the given arguments are valid
     */
    private void checkArguments() throws ParseException {
        // Verify file;
        if (cmd.hasOption('f')) {
            this.file_path = cmd.getOptionValue('f');
            useFile = true;
            System.out.println("Input Type: File\nTaking input file: " + this.file_path + "\n");
        }
        else
        {
            // If no file provided, check for input parameters:
            if (!cmd.hasOption('a') | !cmd.hasOption('c') | !cmd.hasOption('s')) {
                throw new ParseException("No input parameters or file found!");
            } else {
                System.out.println("Input Type: Single Instance\nTaking input params\n");
                // Parse params:
                this.serCreatinin = Double.parseDouble(cmd.getOptionValue('c'));
                this.serSodium = Double.parseDouble(cmd.getOptionValue('s'));
                this.age = Integer.parseInt(cmd.getOptionValue('a'));
            }
        }
    }

    /**
     * Print help if needed
     */
    private void returnHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "Please head over to the README for usage help," +
                " otherwise send me an email, also listed there.\n", options );
    }

    // ------------ Override methods ------------ //

    @Override
    public String getFilePath() {
        return file_path;
    }
    @Override
    public double getSerSodium() {
        return serSodium;
    }
    @Override
    public double getSerCreatinin() {
        return serCreatinin;
    }
    @Override
    public int getAge() {
        return age;
    }

    @Override
    public boolean getInputType() {
        return useFile;
    }
}
