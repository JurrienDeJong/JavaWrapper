READ_ME - JavaWrapper

author: Jurrien de Jong
date: 19-11-2021
version: 1.0
class: BFV3

Quick Note:
    If you are looking for some test data, you can find it at the usage section!

Description of the program:

    The program, JavaWrapper, will create a J48 model which uses a given data file to "train". ( This will happen automatically )
    Using this model, new instances ( patients with their age and serum values ) can be classified.
    The patient either has a high chance of passing away, or will live mostly depending on the serum values.
    A normal serum creatinine value ranges between 0.59 and 1.35 mg/dL while a normal serum sodium value ranges
    between 135 and 145 milliequivalents per liter (mEq/L). Keep this information in mind when viewing the results.

Description of the arguments:
    The program JavaWrapper will handle a few input arguments:

    Multiple Instances in file;
        -f : Input file containing instances yet to be classified.

    If no file is provided, check if single instance is given: ( File always has priority if both options are given )

    Single Instance;
        -a : Age of the patient - in years
        -s : Level of serum creatinine in the blood - in mg/dL
        -c : Level of serum creatinine in the blood - in mEq/L

Usage:
    When opened in Intelli J, please head over to the EDIT CONFIGURATIONS window. In here, you can provide CLI input values.
    When the main method ( WekaEngine ) is run, a single classified instance, or a series of instances will be printed to the user.
    Below a few example arguments can be found;

    ``` Please note that a file has priority ```

    -f datafiles/unknown_data.arff
    ^ Reads the to be classified instances file

    -a 70 -c 2.7 -s 132
    -a 60 -c 0.6 -s 138
    -a 49 -c 1.1 -s 136
    -a 72 -c 1.3 -s 136
    -a 45 -c 1 -s 151
    ^ Single instance arguments

Support:
Please view these pages if you are having trouble with intelliJ, running the command or classes

	https://www.jetbrains.com/help/idea/getting-started.html

	https://www.jetbrains.com/help/idea/run-debug-configuration.html#create-permanent

	https://www.w3schools.com/java/java_classes.asp

For further questions, please send me an email;
ju.de.jong@st.hanze.nl