package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.IOException;

public class WekaManager {

    public static void main(String[] args) {
        WekaManager runner = new WekaManager();
        runner.start();
    }

    private void start(){
        //Read files
        FileReader fileReader = new FileReader();
        String file_path = "kaas.txt";
        Instances data = fileReader.loadArff(file_path);
    }
}
