package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 * This class serves as a Main class. It will get the user input via class CmdLineManager,
 * create a J48 tree and a model, and classify each instance if provided.
 * @author Jurrien de Jong
 * @version 1.0
 */

public class WekaEngine {
    public static void main(String[] args) {
        System.out.println("\n---- Starting Program ----\n");
        WekaEngine runner = new WekaEngine();
        // Get the given options from the user:
        ProvideOptions optionsProvider = new CmdLineManager(args);
        // Start the program!
        runner.start(optionsProvider);
    }


    private void start(ProvideOptions options) {
        // Final variables, the path to the known-data and
        // where we want the model to be placed.
        final String known_instances = "datafiles/data.arff";
        final String model_file = "datafiles/j48.model";

        try {
            // Get the known-instances so the model can "learn":
            Instances data = getData.loadDataFiles(known_instances);
            // Make the J48 Tree
            ConstructTree j48 = new ConstructTree();
            J48 tree = j48.buildTree(data);

            // Save Model;
            weka.core.SerializationHelper.write(model_file, tree);
            // Load Model;
            J48 model = (J48) weka.core.SerializationHelper.read(model_file);

            // Create a classifier object:
            ClassifyInstances Classifier = new ClassifyInstances();
            // Check if a file is provided, if not classify a single instance:
            if (options.getInputType())
            {
                // Classify whole file:
                String filePath = options.getFilePath();
                Instances unknown_data = getData.loadDataFiles(filePath);

                Classifier.classifyInstanceFile(model, unknown_data);
            }
            else
            {
                // The single instance classifier only accepts an array of
                // doubles. So we will create an array with the necessary
                // values here:
                // ( the max amount of values should be 3, so an array is perfect in this situation )
                double[] values = new double[3];
                values[0] = options.getAge();
                values[1] = options.getSerCreatinin();
                values[2] = options.getSerSodium();

                // Pass the model, known-data and value array to the classifier:
                Classifier.classifyNewInstance(model, data, values);
            }
            System.out.println("\n---- End of Program ----");
        } catch (Exception e) {
            // Catch nasty errors.
            System.out.println(e.getMessage());
        }
    }
}