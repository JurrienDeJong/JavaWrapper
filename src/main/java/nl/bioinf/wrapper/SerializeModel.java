package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;

public class SerializeModel {
    protected void saveModel(String modelFile, J48 j48) throws Exception{
            weka.core.SerializationHelper.write(modelFile, j48);
    }
}
