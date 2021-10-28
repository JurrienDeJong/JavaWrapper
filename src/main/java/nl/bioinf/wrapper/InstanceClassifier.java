package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instances;

public class InstanceClassifier {
    protected void classifyNewInstances(J48 model, Instances unknownInstances) throws Exception {
        Instances classified = new Instances(unknownInstances);
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            double label = model.classifyInstance(unknownInstances.instance(i));
            classified.instance(i).setClassValue(label);
        }
        System.out.println("\nNew, labeled = \n" + classified);
    }
}
