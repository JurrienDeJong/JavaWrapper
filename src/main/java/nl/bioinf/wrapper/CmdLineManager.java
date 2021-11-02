package nl.bioinf.wrapper;

import org.apache.commons.cli.*;

public class CmdLineManager implements OptionsProvider{
    private Options options;
    private CommandLine cmd;
    private String file_path;

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
        options.addOption(new Option("f",
                "file",
                true,
                "The file with instances to be classified."));
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
    }

    private void argParse(String[] args) {
        CommandLineParser parser = new BasicParser();
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
        if (!cmd.hasOption('f')) {
            throw new ParseException("No file found/provided");
        } else {
            this.file_path = cmd.getOptionValue('f');
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
}

