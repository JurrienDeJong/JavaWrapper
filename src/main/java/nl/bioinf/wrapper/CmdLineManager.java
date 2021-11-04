package nl.bioinf.wrapper;

import org.apache.commons.cli.*;

public class CmdLineManager implements OptionsProvider{
    protected Options options;
    protected CommandLine cmd;
    protected String file_path;
    protected double serSodium;
    protected double serCreatinin;
    protected int age;
    protected boolean useFile = false;

    public CmdLineManager(String[] args) {
        addOptions();
        argParse(args);
    }

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

    private void argParse(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            this.cmd = parser.parse(options, args);
            if (cmd.hasOption('h')) {
                printHelp();
            }
            verifyArguments();
        } catch (ParseException e) {
            System.out.println("Something went wrong while parsing, cause: "
                    + e.getMessage());
            printHelp();
        }
    }

    private void verifyArguments() throws ParseException {
        if (cmd.hasOption('f')) {
            this.file_path = cmd.getOptionValue('f');
            useFile = true;
            System.out.println("Input Type:\nTaking input file: " + this.file_path + "\n");
        }
        else
        {
            if (!cmd.hasOption('a') | !cmd.hasOption('c') | !cmd.hasOption('s')) {
                throw new ParseException("No input parameters or file found!");
            } else {
                System.out.println("Input Type:\nTaking input params\n");
                this.serCreatinin = Double.parseDouble(cmd.getOptionValue('c'));
                this.serSodium = Double.parseDouble(cmd.getOptionValue('s'));
                this.age = Integer.parseInt(cmd.getOptionValue('a'));
            }
        }
    }

    private void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "java -jar [file.jar] [options]", options );
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
