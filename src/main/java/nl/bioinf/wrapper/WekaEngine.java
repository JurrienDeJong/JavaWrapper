package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.util.Objects;

public class WekaEngine {
    public static void main(String[] args) {
        WekaEngine runner = new WekaEngine();
        OptionsProvider optionsProvider = new CmdLineManager(args);
        runner.start(optionsProvider);
    }


    private void start(OptionsProvider optionsProvider){
        getData getData = new getData();
        final String known_instances = "datafiles/data.arff";
        final String model_file = "datafiles/j48.model";

        try {

            String unknown_instances = optionsProvider.getFilePath();
            Instances unknown_data = getData.loadDataFiles(unknown_instances);


            ConstructTree j48 = new ConstructTree();
            J48 tree = j48.buildTree(data);
            SerializeModel save = new SerializeModel();
            save.saveModel(model_file, tree);
            DeserializeModel load = new DeserializeModel();
            J48 model = load.loadModel(model_file);
            InstanceClassifier Classifier = new InstanceClassifier();
            Classifier.classifyNewInstances(model, unknown_data);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}