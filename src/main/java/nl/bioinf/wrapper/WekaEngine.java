package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instances;

public class WekaEngine {
    public static void main(String[] args) {
        WekaEngine runner = new WekaEngine();
        OptionsProvider optionsProvider = new CmdLineManager(args);
        runner.start(optionsProvider);
    }
    private void start(OptionsProvider optionsProvider){
        FileReader fileReader = new FileReader();
        final String known_instances = "datafiles/weather.nominal.arff";
        final String model_file = "datafiles/j48.model";
        String unknown_instances = optionsProvider.getFilePath();

        try {
            Instances data = fileReader.loadDataFiles(known_instances);
            Instances unknown_data = fileReader.loadDataFiles(unknown_instances);
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