package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 * A simple class which creates a J48 tree
 * @author Jurrien de Jong
 * @version 1.0
 */

public class ConstructTree {
    protected J48 buildTree(Instances instances) throws Exception {
        // Create a string array for tree options:
        String[] options = new String[1];
        // Make an unpruned tree
        options[0] = "-U";
        // Build the tree using the instances and options
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(instances);
        return tree;
    }
}
