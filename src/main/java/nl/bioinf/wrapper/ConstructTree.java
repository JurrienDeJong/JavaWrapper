package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instances;

public class ConstructTree {
    protected J48 buildTree(Instances instances) throws Exception {
        String[] options = new String[1];
        options[0] = "-U";            // unpruned tree
        J48 tree = new J48();         // new instance of tree
        tree.setOptions(options);     // set the options
        tree.buildClassifier(instances);   // build classifier
        return tree;
    }
}
