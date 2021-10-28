package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;

public class DeserializeModel {
    protected J48 loadModel(String modelFile) throws Exception {
        return (J48) weka.core.SerializationHelper.read(modelFile);
    }
}
