package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.util.ArrayList;

public class WekaEngine {
    public static void main(String[] args) {
        System.out.println("---- Starting Program ----\n");
        WekaEngine runner = new WekaEngine();
        OptionsProvider optionsProvider = new CmdLineManager(args);
        runner.start(optionsProvider);
    }


    private void start(OptionsProvider optionsProvider) {
        final String known_instances = "datafiles/data.arff";
        final String model_file = "datafiles/j48.model";

        try {
            Instances data = getData.loadDataFiles(known_instances);
            ConstructTree j48 = new ConstructTree();
            J48 tree = j48.buildTree(data);

            // Save Model;
            weka.core.SerializationHelper.write(model_file, tree);
            // Load Model;
            J48 model = (J48) weka.core.SerializationHelper.read(model_file);

            ClassifyInstances Classifier = new ClassifyInstances();
            // Check if a file is provided, if not classify a single instance:
            if (optionsProvider.getInputType())
            {
                String filePath = optionsProvider.getFilePath();
                Instances unknown_data = getData.loadDataFiles(filePath);

                Classifier.classifyInstanceFile(model, unknown_data);
            }
            else
            {
                ArrayList<Double> values = new ArrayList<>();
                values.add((double) optionsProvider.getAge());
                values.add(optionsProvider.getSerCreatinin());
                values.add(optionsProvider.getSerSodium());
                Classifier.classifyNewInstance(model, data, values);
            }
            System.out.println("\n---- End of Program ----");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}